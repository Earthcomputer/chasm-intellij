package net.earthcomputer.chasmintellij.psi

import com.intellij.psi.tree.IElementType

interface ChasmBinaryExpression : ChasmExpression {
    val left: ChasmExpression
    val operator: IElementType get() = left.node.treeNext.elementType
    val right: ChasmExpression?
}