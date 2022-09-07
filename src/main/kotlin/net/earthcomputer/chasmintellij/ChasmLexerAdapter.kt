package net.earthcomputer.chasmintellij

import com.intellij.lexer.FlexAdapter

class ChasmLexerAdapter : FlexAdapter(ChasmLexer(null)) {
}