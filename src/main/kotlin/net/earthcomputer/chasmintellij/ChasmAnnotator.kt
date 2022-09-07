package net.earthcomputer.chasmintellij

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import net.earthcomputer.chasmintellij.psi.*

class ChasmAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ChasmIdentifier -> annotateIdentifier(element, holder)
        }
    }

    private fun annotateIdentifier(element: ChasmIdentifier, holder: AnnotationHolder) {
        when (val parent = element.parent) {
            is ChasmMapEntry -> {
                if (parent.value is ChasmLambdaExpression) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(ChasmSyntaxHighlighter.FUNCTION)
                        .create()
                } else {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(ChasmSyntaxHighlighter.KEY)
                        .create()
                }
            }
            is ChasmMemberExpression -> {
                if (parent.parent is ChasmCallExpression) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(ChasmSyntaxHighlighter.FUNCTION_CALL)
                        .create()
                } else {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(ChasmSyntaxHighlighter.KEY)
                        .create()
                }
            }
            is ChasmLambdaExpression -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(ChasmSyntaxHighlighter.PARAMETER)
                    .create()
            }
        }
    }
}