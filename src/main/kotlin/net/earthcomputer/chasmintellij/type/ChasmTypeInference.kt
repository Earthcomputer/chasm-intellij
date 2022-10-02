package net.earthcomputer.chasmintellij.type

import com.intellij.openapi.util.Key
import com.intellij.openapi.util.RecursionManager
import com.intellij.openapi.util.UserDataHolderEx
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.util.containers.Stack
import net.earthcomputer.chasmintellij.psi.*
import java.util.concurrent.ConcurrentHashMap

object ChasmTypeInference {
    private const val MAX_FUNCTION_CALL_DEPTH = 16
    private const val MAX_TYPE_COMPLEXITY = 16

    private val FUNCTION_TYPE_KEY = Key<FunctionType>("chasm.FunctionType")
    private val EXPECTED_FUNCTION_TYPE_KEY = Key<ConcurrentHashMap<Int, ExpectedFunctionType>>("chasm.ExpectedFunctionType")
    private val INFER_GUARD = RecursionManager.createGuard<Pair<ChasmExpression, Map<ChasmLambdaExpression, ChasmType?>>>("chasm.typeInference")
    private val EXPECTED_TYPE_GUARD = RecursionManager.createGuard<ChasmExpression>("chasm.expectedType")

    private val CURRENT_PARAMETER_TYPES = ThreadLocal.withInitial { Stack<Map<ChasmLambdaExpression, ChasmType?>>() }
    private val currentParameterTypes get() = CURRENT_PARAMETER_TYPES.get().let { if (it.isNotEmpty()) it.peek() else emptyMap() }

    private fun getFunctionType(expression: ChasmLambdaExpression): ChasmType {
        return (expression as UserDataHolderEx).putUserDataIfAbsent(FUNCTION_TYPE_KEY, FunctionType(expression))
    }
    
    private fun getExpectedFunctionType(expression: ChasmExpression, index: Int = 0): ChasmType {
        return (expression as UserDataHolderEx).putUserDataIfAbsent(EXPECTED_FUNCTION_TYPE_KEY, ConcurrentHashMap())
            .computeIfAbsent(index) { ExpectedFunctionType(expression, index) }
    }

    fun infer(expression: ChasmExpression): ChasmType? {
        return CachedValuesManager.getCachedValue(expression) {
            CachedValueProvider.Result(inferUncached(expression), PsiModificationTracker.MODIFICATION_COUNT)
        }
    }

    private fun inferUncached(expression: ChasmExpression): ChasmType? {
        return INFER_GUARD.doPreventingRecursion(expression to currentParameterTypes, true) {
            val result = doInfer(expression)
            if (result != null && result.possibilities > MAX_TYPE_COMPLEXITY) {
                INFER_GUARD.prohibitResultCaching(expression to currentParameterTypes)
                return@doPreventingRecursion null
            }
            result
        }
    }

    private fun doInfer(expression: ChasmExpression): ChasmType? {
        return when (expression) {
            is ChasmMapExpression -> MapType(
                expression.mapEntryList.mapNotNull { entry -> entry.key?.let { it to entry } }.associate { (key, entry) -> key to MapEntryResolution(entry.value?.let(::inferUncached), setOf(entry)) },
                emptyMap()
            )
            is ChasmListExpression -> ListType(ChasmType.union(expression.expressionList.mapNotNull(::inferUncached)))
            is ChasmTernaryExpression -> ChasmType.union(listOfNotNull(expression.trueExpr?.let(::inferUncached), expression.falseExpr?.let(::inferUncached)))
            is ChasmReferenceExpression -> when (val resolved = expression.reference.resolve()) {
                is ChasmMapEntry -> resolved.value?.let(::inferUncached)
                is ChasmLambdaExpression -> currentParameterTypes[resolved]
                else -> null
            }
            is ChasmCallExpression -> {
                if (CURRENT_PARAMETER_TYPES.get().size >= MAX_FUNCTION_CALL_DEPTH) {
                    INFER_GUARD.prohibitResultCaching(expression to currentParameterTypes)
                    return null
                }
                val functionType = inferUncached(expression.function)
                val argType = expression.argument?.let(::inferUncached)
                functionType?.map { type -> (type as? FunctionType)?.let { funcType ->
                    CURRENT_PARAMETER_TYPES.get().push(currentParameterTypes + (funcType.expression to argType))
                    try {
                        funcType.expression.body?.let(::inferUncached)
                    } finally {
                        CURRENT_PARAMETER_TYPES.get().pop()
                    }
                } }
            }
            is ChasmIndexExpression -> {
                val ownerType = inferUncached(expression.owner) ?: return null
                val indexType = expression.index?.let(::inferUncached) ?: return null
                ownerType.biMap(indexType) { ownerTyp, indexTyp -> when (indexTyp) {
                    is FunctionType -> ownerTyp
                    is IntType -> (ownerTyp as? ListType)?.elementType
                    else -> null
                } }
            }
            is ChasmParenthesesExpression -> expression.expression?.let(::inferUncached)
            is ChasmLambdaExpression -> getFunctionType(expression)
            is ChasmLiteralExpression -> when (expression.value) {
                is String -> StringType
                is Long -> IntType
                is Double -> FloatType
                is Boolean -> BoolType
                else -> null
            }
            is ChasmUnaryExpression -> expression.operand?.let(::inferUncached)?.map { unaryOpType(it, expression.operator) }
            is ChasmBinaryExpression -> {
                val leftType = inferUncached(expression.left)
                val rightType = expression.right?.let(::inferUncached)
                when {
                    leftType == null -> rightType?.map { binaryOpType(null, it, expression) }
                    rightType == null -> leftType.map { binaryOpType(it, null, expression) }
                    else -> leftType.biMap(rightType) { left, right -> binaryOpType(left, right, expression) }
                }
            }
            is ChasmMemberExpression -> {
                val memberName = expression.memberName ?: return null
                inferUncached(expression.expression)?.map { parentType ->
                    if (parentType !is MapType) {
                        return@map null
                    }
                    (parentType.definiteValues[memberName] ?: parentType.possibleValues[memberName])?.type
                }
            }
            else -> throw UnsupportedOperationException("Unknown expression type $expression")
        }
    }

    private fun unaryOpType(type: ChasmType?, token: IElementType): ChasmType? {
        return when (token) {
            ChasmTypes.PLUS, ChasmTypes.MINUS -> type?.takeIf { it == IntType || it == FloatType }
            ChasmTypes.NOT -> type?.takeIf { it == BoolType }
            ChasmTypes.INVERT -> type?.takeIf { it == IntType }
            else -> throw UnsupportedOperationException("Unknown unary token: $token")
        }
    }

    private fun binaryOpType(leftType: ChasmType?, rightType: ChasmType?, expression: ChasmBinaryExpression): ChasmType? {
        return when (expression) {
            is ChasmEqualityExpression,
            is ChasmRelationalExpression,
            is ChasmBooleanAndExpression,
            is ChasmBooleanOrExpression -> BoolType
            is ChasmBitwiseOrExpression,
            is ChasmBitwiseAndExpression,
            is ChasmBitwiseXorExpression,
            is ChasmShiftExpression -> IntType
            else -> when {
                expression.operator == ChasmTypes.PLUS && leftType is MapType && rightType is MapType -> ChasmType.union(listOf(leftType, rightType))
                expression.operator == ChasmTypes.PLUS && (leftType is StringType || rightType is StringType) -> StringType
                leftType is IntType && rightType is IntType -> IntType
                else -> FloatType
            }
        }
    }

    fun expectedType(expression: ChasmExpression): ChasmType? {
        return CachedValuesManager.getCachedValue(expression) {
            CachedValueProvider.Result(expectedTypeUncached(expression), PsiModificationTracker.MODIFICATION_COUNT)
        }
    }

    private fun expectedTypeUncached(expression: ChasmExpression): ChasmType? {
        return EXPECTED_TYPE_GUARD.doPreventingRecursion(expression, true) {
            val result = doExpectedType(expression)
            if (result != null && result.possibilities > MAX_TYPE_COMPLEXITY) {
                EXPECTED_TYPE_GUARD.prohibitResultCaching(expression)
                return@doPreventingRecursion null
            }
            result
        }
    }

    private fun doExpectedType(expression: ChasmExpression): ChasmType? {
        return when (val parent = expression.parent) {
            is ChasmMemberExpression -> {
                val memberName = parent.memberName ?: return MapType(emptyMap(), emptyMap())
                MapType(mapOf(memberName to MapEntryResolution(expectedTypeUncached(parent), emptySet())), emptyMap())
            }
            is ChasmCallExpression -> {
                if (expression == parent.function) {
                    getExpectedFunctionType(parent)
                } else {
                    when (val funcType = infer(parent.function)) {
                        is UnionType -> ChasmType.intersection(funcType.types.asSequence().filterIsInstance<FunctionType>().mapNotNull { expectedArgumentType(it.expression) }.asIterable())
                        is FunctionType -> expectedArgumentType(funcType.expression)
                        else -> null
                    }
                }
            }
            is ChasmIndexExpression -> {
                if (expression == parent.owner) {
                    parent.index?.let(::infer)?.map { typ ->
                        when (typ) {
                            is FunctionType -> expectedTypeUncached(parent)?.map { it.takeIf { it is ListType } }
                            is IntType -> ListType(expectedTypeUncached(parent))
                            is StringType -> MapType(emptyMap(), emptyMap())
                            else -> null
                        }
                    } ?: ChasmType.union(listOf(ListType(null), MapType(emptyMap(), emptyMap())))
                } else {
                    infer(parent.owner)?.map { typ ->
                        when (typ) {
                            is ListType -> ChasmType.union(listOf(IntType, getExpectedFunctionType(parent)))
                            is MapType -> StringType
                            else -> ChasmType.union(listOf(IntType, StringType, getExpectedFunctionType(parent)))
                        }
                    }
                }
            }
            is ChasmMapEntry -> {
                val granny = parent.parent as? ChasmMapExpression ?: return null
                val grannyType = expectedTypeUncached(granny) ?: return null
                grannyType.map { grannyTyp ->
                    if (grannyTyp !is MapType) {
                        return@map null
                    }
                    (grannyTyp.definiteValues[parent.key] ?: grannyTyp.possibleValues[parent.key])?.type
                }
            }
            is ChasmListExpression -> {
                expectedTypeUncached(parent)?.map { parentType ->
                    (parentType as? ListType)?.elementType
                }
            }
            is ChasmLambdaExpression -> expectedReturnType(parent)
            is ChasmParenthesesExpression -> expectedTypeUncached(parent)
            is ChasmTernaryExpression -> {
                if (expression == parent.condition) {
                    BoolType
                } else {
                    expectedTypeUncached(parent)
                }
            }
            is ChasmUnaryExpression -> unaryExpectedType(parent)
            is ChasmBinaryExpression -> binaryExpectedType(expression, parent)
            else -> null
        }
    }

    private fun unaryExpectedType(parent: ChasmUnaryExpression): ChasmType {
        return expectedTypeUncached(parent)?.map { parentType ->
            when (parent.operator) {
                ChasmTypes.NOT -> parentType.takeIf { it == BoolType }
                ChasmTypes.INVERT -> parentType.takeIf { it == IntType }
                else -> parentType.takeIf { it == IntType || it == FloatType }
            }
        } ?: when (parent.operator) {
            ChasmTypes.NOT -> BoolType
            ChasmTypes.INVERT -> IntType
            else -> ChasmType.union(listOf(IntType, FloatType))!!
        }
    }

    private fun binaryExpectedType(self: ChasmExpression, parent: ChasmBinaryExpression): ChasmType? {
        return when (parent) {
            is ChasmBooleanAndExpression,
            is ChasmBooleanOrExpression -> BoolType
            is ChasmBitwiseAndExpression,
            is ChasmBitwiseOrExpression,
            is ChasmBitwiseXorExpression,
            is ChasmShiftExpression -> IntType
            is ChasmEqualityExpression -> {
                (if (self == parent.left) parent.right else parent.left)?.let(::infer)
            }
            is ChasmRelationalExpression -> {
                (if (self == parent.left) parent.right else parent.left)?.let(::infer)?.map { it.takeIf { it == IntType || it == FloatType } }
                    ?: ChasmType.union(listOf(IntType, FloatType))
            }
            else -> {
                val otherType = (if (self == parent.left) parent.right else parent.left)?.let(::infer)
                if (parent.operator == ChasmTypes.PLUS) {
                    otherType?.map { otherTyp ->
                        when (otherTyp) {
                            is StringType -> StringType
                            is IntType, is FloatType, is MapType -> otherTyp
                            else -> null
                        }
                    } ?: ChasmType.union(listOf(IntType, FloatType, MapType(emptyMap(), emptyMap())))
                } else {
                    otherType?.map { it.takeIf { it == IntType || it == FloatType } }
                        ?: expectedTypeUncached(parent)?.takeIf { it == IntType || it == FloatType }
                }
            }
        }
    }

    fun expectedArgumentType(lambda: ChasmLambdaExpression): ChasmType? {
        return ChasmType.intersection(ReferencesSearch.search(lambda).asSequence().filterIsInstance<ChasmExpression>().mapNotNull(::expectedTypeUncached).asIterable())
    }

    fun expectedReturnType(lambda: ChasmLambdaExpression): ChasmType? {
        val lambdaType = expectedTypeUncached(lambda)
        return ChasmType.intersection(((lambdaType as? UnionType)?.types ?: setOf(lambdaType))
            .asSequence()
            .filterIsInstance<ExpectedFunctionType>()
            .mapNotNull { lambdaTyp ->
                when (lambdaTyp.expression) {
                    is ChasmCallExpression -> expectedTypeUncached(lambdaTyp.expression)
                    is ChasmIndexExpression -> BoolType
                    else -> null
                }
            }
            .asIterable())
    }
}