package net.earthcomputer.chasmintellij.formatter

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.tree.TokenSet
import net.earthcomputer.chasmintellij.ChasmLanguage
import net.earthcomputer.chasmintellij.psi.ChasmListExpression
import net.earthcomputer.chasmintellij.psi.ChasmMapExpression
import net.earthcomputer.chasmintellij.psi.ChasmTypes

class ChasmBlock(
    node: ASTNode,
    wrap: Wrap?,
    alignment: Alignment?,
    private val myIndent: Indent?,
    private val mySettings: CodeStyleSettings,
    private val mySpacingBuilder: SpacingBuilder,
) : AbstractBlock(node, wrap, alignment) {
    companion object {
        private val LIST_BRACKET_TOKENS = TokenSet.create(ChasmTypes.LBRACE, ChasmTypes.RBRACE, ChasmTypes.LBRACKET, ChasmTypes.RBRACKET)
    }

    private val myPsi = node.psi

    override fun getSpacing(child1: Block?, child2: Block): Spacing? {
        return mySpacingBuilder.getSpacing(this, child1, child2)
    }

    override fun getIndent() = myIndent

    override fun isLeaf() = myNode.firstChildNode == null

    override fun buildChildren(): List<Block> {
        var node: ASTNode? = myNode.firstChildNode
        val children = mutableListOf<Block>()

        while (node != null) {
            if (node.elementType != TokenType.WHITE_SPACE && node.textLength != 0) {
                children += makeChild(node)
            }
            node = node.treeNext
        }

        return children
    }

    private fun makeChild(node: ASTNode): ChasmBlock {
        val commonSettings = mySettings.getCommonSettings(ChasmLanguage)
        val chasmSettings = mySettings.getCustomSettings(ChasmCodeStyleSettings::class.java)

        var indent = Indent.getNoneIndent()
        var alignment: Alignment? = null
        var wrap: Wrap? = null

        if (myPsi is ChasmMapExpression || myPsi is ChasmListExpression) {
            if (node.elementType == ChasmTypes.COMMA) {
                wrap = Wrap.createWrap(WrapType.NONE, true)
            } else if (node.elementType !in LIST_BRACKET_TOKENS) {
                wrap = Wrap.createWrap(if (myPsi is ChasmMapExpression) chasmSettings.MAP_WRAPPING else chasmSettings.LIST_WRAPPING, true)
                indent = Indent.getNormalIndent()
            }
        }

        return ChasmBlock(node, wrap, alignment, indent, mySettings, mySpacingBuilder)
    }

    override fun isIncomplete(): Boolean {
        return when (myPsi) {
            is ChasmMapExpression -> node.lastChildNode.elementType != ChasmTypes.RBRACE
            is ChasmListExpression -> node.lastChildNode.elementType != ChasmTypes.RBRACKET
            else -> false
        }
    }

    override fun getChildIndent(): Indent? {
        if (myPsi is ChasmMapExpression || myPsi is ChasmListExpression) {
            return Indent.getNormalIndent()
        }
        return null
    }
}