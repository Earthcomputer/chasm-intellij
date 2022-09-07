package net.earthcomputer.chasmintellij.psi

import com.intellij.psi.tree.IElementType
import net.earthcomputer.chasmintellij.ChasmLanguage

class ChasmTokenType(debugName: String) : IElementType(debugName, ChasmLanguage) {
    override fun toString() = "ChasmTokenType." + super.toString()
}