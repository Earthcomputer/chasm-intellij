package net.earthcomputer.chasmintellij

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement
import net.earthcomputer.chasmintellij.psi.ChasmLambdaExpression
import net.earthcomputer.chasmintellij.psi.ChasmMapEntry
import net.earthcomputer.chasmintellij.psi.ChasmReferenceExpression

class ChasmRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean {
        return element is ChasmMapEntry || element is ChasmLambdaExpression || element is ChasmReferenceExpression
    }
}