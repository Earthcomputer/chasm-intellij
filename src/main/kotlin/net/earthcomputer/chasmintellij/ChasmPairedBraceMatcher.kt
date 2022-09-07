package net.earthcomputer.chasmintellij

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import net.earthcomputer.chasmintellij.psi.ChasmTypes

class ChasmPairedBraceMatcher : PairedBraceMatcher {
    companion object {
        private val PAIRS = arrayOf(
            BracePair(ChasmTypes.LBRACE, ChasmTypes.RBRACE, true),
            BracePair(ChasmTypes.LBRACKET, ChasmTypes.RBRACKET, false),
            BracePair(ChasmTypes.LPAREN, ChasmTypes.RPAREN, false),
        )
    }

    override fun getPairs() = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset
}