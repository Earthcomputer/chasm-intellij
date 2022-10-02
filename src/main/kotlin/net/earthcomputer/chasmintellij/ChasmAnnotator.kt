package net.earthcomputer.chasmintellij

import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationBuilder
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import net.earthcomputer.chasmintellij.psi.*

class ChasmAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ChasmIdentifier -> annotateIdentifier(element, holder)
            is ChasmLiteralExpression -> annotateLiteral(element, holder)
        }
    }

    private fun annotateIdentifier(element: ChasmIdentifier, holder: AnnotationHolder) {
        when (val parent = element.parent) {
            is ChasmMapEntry -> annotateMapEntryKey(parent, holder, null)
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

    private fun annotateLiteral(element: ChasmLiteralExpression, holder: AnnotationHolder) {
        when (val parent = element.parent) {
            is ChasmMapEntry -> {
                if (element == parent.keyLiteral) {
                    annotateMapEntryKey(parent, holder, element.node.getChildren(null).filter { it.elementType != ChasmTypes.ESCAPED_STRING })
                }
            }
        }
    }

    private fun annotateMapEntryKey(parent: ChasmMapEntry, holder: AnnotationHolder, nodes: Iterable<ASTNode>?) {
        val annotationBuilderCreator: () -> AnnotationBuilder = if (parent.value is ChasmLambdaExpression) {
            {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(ChasmSyntaxHighlighter.FUNCTION)
            }
        } else {
            {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .textAttributes(ChasmSyntaxHighlighter.KEY)
            }
        }
        if (nodes == null) {
            annotationBuilderCreator().create()
        } else {
            for (node in nodes) {
                annotationBuilderCreator().range(node).create()
            }
        }
    }
}