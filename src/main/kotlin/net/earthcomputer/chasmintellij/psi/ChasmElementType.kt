package net.earthcomputer.chasmintellij.psi

import com.intellij.psi.tree.IElementType
import net.earthcomputer.chasmintellij.ChasmLanguage

class ChasmElementType(debugName: String) : IElementType(debugName, ChasmLanguage) {
    override fun toString() = "ChasmElementType." + super.toString()
}