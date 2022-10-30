package net.earthcomputer.chasmintellij

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import net.earthcomputer.chasmintellij.psi.ChasmTypes

class ChasmQuoteHandler : SimpleTokenSetQuoteHandler(ChasmTypes.QUOTE, ChasmTypes.SINGLE_QUOTE, ChasmTypes.BACKTICK) {
}