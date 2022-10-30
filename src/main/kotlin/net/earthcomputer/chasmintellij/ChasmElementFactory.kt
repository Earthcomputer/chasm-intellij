package net.earthcomputer.chasmintellij

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.text.StringUtil
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
        val text = if (StringUtil.isJavaIdentifier(name)) {
            name
        } else {
            "`${name.replace("\\", "\\\\").replace("`", "\\`")}`"
        }
        return (createExpression(text) as ChasmReferenceExpression).referenceElement
    }
}

val Project.chasmElementFactory get() = ChasmElementFactory(this)
