package net.earthcomputer.chasmintellij

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import net.earthcomputer.chasmintellij.psi.ChasmFile
import net.earthcomputer.chasmintellij.psi.ChasmTypes

class ChasmParserDefinition : ParserDefinition {
    companion object {
        private val COMMENTS = TokenSet.create(ChasmTypes.LINE_COMMENT, ChasmTypes.INLINE_COMMENT, ChasmTypes.COMMENT_INLINE_START, ChasmTypes.COMMENT_INLINE_END, ChasmTypes.DOC_COMMENT, ChasmTypes.DOC_COMMENT_INLINE_START, ChasmTypes.DOC_COMMENT_INLINE_END)
        private val STRING_LITERALS = TokenSet.create(ChasmTypes.UNESCAPED_STRING, ChasmTypes.ESCAPED_STRING)
        val FILE = IFileElementType(ChasmLanguage)
    }

    override fun createLexer(project: Project?) = ChasmLexerAdapter()

    override fun createParser(project: Project?) = ChasmParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = COMMENTS

    override fun getStringLiteralElements() = STRING_LITERALS

    override fun createElement(node: ASTNode): PsiElement = ChasmTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = ChasmFile(viewProvider)
}