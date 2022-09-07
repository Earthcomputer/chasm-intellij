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
        private val STRING_LITERALS = TokenSet.create(ChasmTypes.STRING, ChasmTypes.CHAR)
        val FILE = IFileElementType(ChasmLanguage)
    }

    override fun createLexer(project: Project?) = ChasmLexerAdapter()

    override fun createParser(project: Project?) = ChasmParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens(): TokenSet = TokenSet.EMPTY

    override fun getStringLiteralElements() = STRING_LITERALS

    override fun createElement(node: ASTNode): PsiElement = ChasmTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = ChasmFile(viewProvider)
}