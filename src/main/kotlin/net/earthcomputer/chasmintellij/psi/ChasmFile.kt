package net.earthcomputer.chasmintellij.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import net.earthcomputer.chasmintellij.ChasmFileType
import net.earthcomputer.chasmintellij.ChasmLanguage

class ChasmFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ChasmLanguage) {
    override fun getFileType() = ChasmFileType
    override fun toString() = "Chassembly File"

    val expression get() = findChildByClass(ChasmExpression::class.java)
}