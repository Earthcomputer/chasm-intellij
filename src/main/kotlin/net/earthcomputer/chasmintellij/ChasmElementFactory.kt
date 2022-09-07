package net.earthcomputer.chasmintellij

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import net.earthcomputer.chasmintellij.psi.ChasmExpression
import net.earthcomputer.chasmintellij.psi.ChasmFile
import net.earthcomputer.chasmintellij.psi.ChasmIdentifier
import net.earthcomputer.chasmintellij.psi.ChasmReferenceExpression

class ChasmElementFactory(private val project: Project) {
    fun createFile(text: String): ChasmFile {
        return PsiFileFactory.getInstance(project).createFileFromText("dummy.chasm", ChasmFileType, text) as ChasmFile
    }

    fun createExpression(text: String): ChasmExpression {
        return createFile(text).expression!!
    }

    fun createIdentifier(name: String): ChasmIdentifier {
        return (createExpression(name) as ChasmReferenceExpression).referenceElement
    }
}

val Project.chasmElementFactory get() = ChasmElementFactory(this)
