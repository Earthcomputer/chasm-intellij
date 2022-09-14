package net.earthcomputer.chasmintellij.type

import net.earthcomputer.chasmintellij.psi.ChasmExpression
import net.earthcomputer.chasmintellij.psi.ChasmLambdaExpression
import net.earthcomputer.chasmintellij.psi.ChasmMapEntry

sealed class ChasmType {
    open fun map(transformer: (ChasmType) -> ChasmType?): ChasmType? {
        return transformer(this)
    }

    open fun biMap(other: ChasmType, transformer: (ChasmType, ChasmType) -> ChasmType?): ChasmType? {
        return if (other is UnionType) {
            union(other.types.mapNotNull { transformer(this, it) })
        } else {
            transformer(this, other)
        }
    }

    open val possibilities = 1

    companion object {
        fun intersection(types: Iterable<ChasmType>): ChasmType? {
            return types.map { (it as? UnionType)?.types ?: setOf(it) }.reduceOrNull(::intersection)?.let(::union)
        }

        private fun intersection(types1: Set<ChasmType>, types2: Set<ChasmType>): Set<ChasmType> {
            val map1 = types1.filterIsInstance<MapType>().firstOrNull()
            val map2 = types2.filterIsInstance<MapType>().firstOrNull()
            val list1 = types1.filterIsInstance<ListType>().firstOrNull()
            val list2 = types2.filterIsInstance<ListType>().firstOrNull()
            return types1.asSequence().filter(types2::contains).toSet() +
                    setOfNotNull(map1?.let { map2?.let { MapType(
                        map1.definiteValues + map2.definiteValues,
                        map1.possibleValues.filterKeys { !map2.definiteValues.containsKey(it) } + map2.possibleValues.filterKeys { !map1.definiteValues.containsKey(it) }
                    ) } }) +
                    setOfNotNull(list1?.elementType?.let { list2?.elementType?.let { intersection(listOf(list1.elementType, list2.elementType))?.let(::ListType) } }) +
                    types1.filter { it is FunctionType || it is ExpectedFunctionType } +
                    types2.filter { it is FunctionType || it is ExpectedFunctionType }
        }

        fun union(types: Iterable<ChasmType>): ChasmType? {
            var listType: ListType? = null
            var mapType: MapType? = null
            val allTypes = mutableSetOf<ChasmType>()
            for (type_ in types) {
                for (type in (type_ as? UnionType)?.types ?: setOf(type_)) {
                    when (type) {
                        is MapType -> {
                            mapType = if (mapType == null) {
                                type
                            } else {
                                val newDefiniteValues = mutableMapOf<String, MapEntryResolution>()
                                val newPossibleValues = mutableMapOf<String, MapEntryResolution>()
                                for ((key, value) in type.definiteValues) {
                                    val otherValue = mapType.definiteValues[key]
                                    if (otherValue != null) {
                                        newDefiniteValues[key] = value.union(otherValue)
                                    } else {
                                        val otherPossibleValue = mapType.possibleValues[key]
                                        if (otherPossibleValue != null) {
                                            newPossibleValues[key] = value.union(otherPossibleValue)
                                        } else {
                                            newPossibleValues[key] = value
                                        }
                                    }
                                }
                                for ((key, value) in type.possibleValues) {
                                    val otherValue = mapType.definiteValues[key] ?: mapType.possibleValues[key]
                                    if (otherValue != null) {
                                        newPossibleValues[key] = value.union(otherValue)
                                    } else {
                                        newPossibleValues[key] = value
                                    }
                                }
                                for ((key, value) in mapType.definiteValues) {
                                    if (type.definiteValues.containsKey(key) || type.possibleValues.containsKey(key)) {
                                        continue
                                    }
                                    newPossibleValues[key] = value
                                }
                                for ((key, value) in mapType.possibleValues) {
                                    if (type.definiteValues.containsKey(key) || type.possibleValues.containsKey(key)) {
                                        continue
                                    }
                                    newPossibleValues[key] = value
                                }
                                MapType(newDefiniteValues, newPossibleValues)
                            }
                        }
                        is ListType -> {
                            listType = if (listType == null) {
                                type
                            } else {
                                ListType(union(listOfNotNull(type.elementType, listType.elementType)))
                            }
                        }
                        else -> {
                            allTypes += type
                        }
                    }
                }
            }
            mapType?.let { allTypes += it }
            listType?.let { allTypes += it }
            return when (allTypes.size) {
                0 -> null
                1 -> allTypes.iterator().next()
                else -> UnionType(allTypes)
            }
        }
    }
}

object BoolType : ChasmType()
object IntType : ChasmType()
object FloatType : ChasmType()
object StringType : ChasmType()

data class ListType(val elementType: ChasmType?) : ChasmType()

data class MapType(
    val definiteValues: Map<String, MapEntryResolution>,
    val possibleValues: Map<String, MapEntryResolution>,
) : ChasmType()

data class MapEntryResolution(val type: ChasmType?, val entryPsis: Set<ChasmMapEntry>) {
    internal fun union(other: MapEntryResolution): MapEntryResolution {
        return MapEntryResolution(ChasmType.union(listOfNotNull(type, other.type)), entryPsis + other.entryPsis)
    }
}

class FunctionType(val expression: ChasmLambdaExpression) : ChasmType() {
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}

class ExpectedFunctionType(val expression: ChasmExpression, val index: Int) : ChasmType() {
    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}

data class UnionType internal constructor(val types: Set<ChasmType>) : ChasmType() {
    override fun map(transformer: (ChasmType) -> ChasmType?): ChasmType? {
        return union(types.mapNotNull(transformer))
    }

    override fun biMap(other: ChasmType, transformer: (ChasmType, ChasmType) -> ChasmType?): ChasmType? {
        val newTypes = types.flatMap { type1 ->
            if (other is UnionType) {
                other.types.mapNotNull { transformer(type1, it) }
            } else {
                listOfNotNull(transformer(type1, other))
            }
        }
        return union(newTypes)
    }

    override val possibilities = types.size
}
