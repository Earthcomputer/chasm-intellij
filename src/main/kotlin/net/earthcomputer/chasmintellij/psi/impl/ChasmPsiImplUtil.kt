@file:JvmName("ChasmPsiImplUtil")

package net.earthcomputer.chasmintellij.psi.impl

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.*
import com.intellij.util.containers.map2Array
import net.earthcomputer.chasmintellij.ChasmIcons
import net.earthcomputer.chasmintellij.chasmElementFactory
import net.earthcomputer.chasmintellij.psi.*
import net.earthcomputer.chasmintellij.type.ChasmTypeInference
import net.earthcomputer.chasmintellij.type.MapType
import net.earthcomputer.chasmintellij.type.UnionType
import kotlin.streams.asSequence

fun getKeyLiteral(entry: ChasmMapEntry): ChasmLiteralExpression? {
    return entry.firstChild as? ChasmLiteralExpression
}

fun getKeyElement(entry: ChasmMapEntry): PsiElement {
    return entry.keyIdentifier ?: entry.keyLiteral!!
}

fun getKey(entry: ChasmMapEntry): String? {
    return entry.keyIdentifier?.text ?: entry.keyLiteral!!.value as? String
}

fun getValue(entry: ChasmMapEntry): ChasmExpression? {
    return entry.expressionList.lastOrNull()?.takeIf { it != entry.keyLiteral }
}

fun getName(entry: ChasmMapEntry): String? {
    return getKey(entry)
}

fun setName(entry: ChasmMapEntry, name: String): ChasmMapEntry {
    entry.keyElement.replace(entry.project.chasmElementFactory.createIdentifier(name))
    return entry
}

fun getReferenceName(reference: ChasmReferenceExpression): String {
    return reference.referenceElement.text
}

fun isGlobal(reference: ChasmReferenceExpression): Boolean {
    return reference.node.findChildByType(ChasmTypes.DOLLAR) != null
}

fun getReference(reference: ChasmReferenceExpression): PsiReference {
    return CachedValuesManager.getCachedValue(reference) {
        CachedValueProvider.Result(object : PsiReferenceBase<ChasmReferenceExpression>(reference, TextRange(0, reference.textLength)) {
            override fun resolve(): PsiElement? {
                return resolve(element)
            }

            override fun getVariants(): Array<Any> {
                return collectVariants(element)
            }

            override fun handleElementRename(newElementName: String): PsiElement {
                return handleElementRename(element, newElementName)
            }
        }, PsiModificationTracker.MODIFICATION_COUNT)
    }
}

private fun resolve(reference: ChasmReferenceExpression): PsiElement? {
    val name = reference.referenceName
    val sequence = reference.parents(false)
        .takeWhile { it !is ChasmFile }
        .mapNotNull { element ->
            when (element) {
                is ChasmMapExpression -> element.mapEntryList.firstOrNull { it.key == name }
                is ChasmLambdaExpression -> element.takeIf { it.argumentName == name }
                else -> null
            }
        }

    return if (reference.isGlobal) {
        sequence.lastOrNull()
    } else {
        sequence.firstOrNull()
    }
}

private fun collectVariants(reference: ChasmReferenceExpression): Array<Any> {
    val variants = mutableMapOf<String, PsiElement>()
    val parents = reference.parents(false).takeWhile { it !is ChasmFile }.toMutableList()
    if (reference.isGlobal) {
        parents.reverse()
    }
    for (parent in parents) {
        when (parent) {
            is ChasmMapExpression -> {
                for (entry in parent.mapEntryList) {
                    val key = entry.key ?: continue
                    variants.putIfAbsent(key, entry)
                }
            }
            is ChasmLambdaExpression -> variants.putIfAbsent(parent.argumentName, parent)
        }
    }

    val prefix = if (reference.isGlobal) "$" else ""
    return variants.values.map2Array {
        LookupElementBuilder.create(it, prefix + (it as PsiNamedElement).name)
            .withIcon(ChasmIcons.FILE)
            .withTypeText(it.containingFile.name)
    }
}

private fun handleElementRename(reference: ChasmReferenceExpression, name: String): ChasmReferenceExpression {
    reference.referenceElement.replace(reference.project.chasmElementFactory.createIdentifier(name))
    return reference
}

fun getReference(memberExpr: ChasmMemberExpression): PsiReference? {
    val memberNameElement = memberExpr.memberNameElement ?: return null
    val memberName = memberNameElement.text
    return CachedValuesManager.getCachedValue(memberExpr) {
        CachedValueProvider.Result(object : PsiPolyVariantReferenceBase<ChasmMemberExpression>(memberExpr, memberNameElement.textRangeInParent) {
            override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
                return resolve(element, memberName)
            }

            override fun getVariants(): Array<Any> {
                return collectVariants(element)
            }

            override fun handleElementRename(newElementName: String): PsiElement {
                return handleElementRename(element, newElementName)
            }
        }, PsiModificationTracker.MODIFICATION_COUNT)
    }
}

private fun resolve(memberExpr: ChasmMemberExpression, memberName: String): Array<ResolveResult> {
    val ownerType = ChasmTypeInference.infer(memberExpr.expression) ?: return ResolveResult.EMPTY_ARRAY
    return ((ownerType as? UnionType)?.types ?: setOf(ownerType)).asSequence()
        .filterIsInstance<MapType>()
        .mapNotNull { mapType -> mapType.definiteValues[memberName] ?: mapType.possibleValues[memberName] }
        .flatMap { it.entryPsis.asSequence() }
        .map(::PsiElementResolveResult)
        .toList()
        .toTypedArray()
}

private fun collectVariants(memberExpr: ChasmMemberExpression): Array<Any> {
    val ownerType = ChasmTypeInference.infer(memberExpr.expression) ?: return emptyArray()
    return ((ownerType as? UnionType)?.types ?: setOf(ownerType)).asSequence()
        .filterIsInstance<MapType>()
        .flatMap { it.definiteValues.keys.asSequence() + it.possibleValues.keys.asSequence() }
        .distinct()
        .map { LookupElementBuilder.create(it).withIcon(ChasmIcons.FILE) }
        .toList()
        .toTypedArray()
}

private fun handleElementRename(memberExpr: ChasmMemberExpression, name: String): ChasmMemberExpression {
    memberExpr.memberNameElement?.replace(memberExpr.project.chasmElementFactory.createIdentifier(name))
    return memberExpr
}

fun getArgumentName(lambda: ChasmLambdaExpression): String {
    return lambda.argumentElement.text
}

fun getName(lambda: ChasmLambdaExpression): String {
    return getArgumentName(lambda)
}

fun setName(lambda: ChasmLambdaExpression, name: String): ChasmLambdaExpression {
    lambda.argumentElement.replace(lambda.project.chasmElementFactory.createIdentifier(name))
    return lambda
}

fun getMemberName(memberExpr: ChasmMemberExpression): String? {
    return memberExpr.memberNameElement?.text
}

fun getOperator(unaryExpr: ChasmUnaryExpression): IElementType {
    return unaryExpr.node.firstChildNode.elementType
}

fun getReferences(literal: ChasmLiteralExpression): Array<PsiReference> {
    return PsiReferenceService.getService().getContributedReferences(literal)
}

fun getValue(literal: ChasmLiteralExpression): Any? {
    val text = literal.text.trim()
    return when {
        text.startsWith("\"") -> text.substring(1, text.length - 1).replace("\\\"", "\"").replace("\\\\", "\\")
        text.startsWith("'") -> text.substring(1, text.length - 1)
            .replace("\\'", "'")
            .replace("\\\\", "\\")
            .chars()
            .asSequence()
            .singleOrNull()
            ?.toLong()
        text == "false" -> false
        text == "true" -> true
        text == "null" -> null
        text.contains('.') -> text.toDoubleOrNull()
        else -> text.toLongOrNull()
    }
}
