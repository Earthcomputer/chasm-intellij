@file:JvmName("ChasmPsiImplUtil")

package net.earthcomputer.chasmintellij.psi.impl

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import com.intellij.psi.util.parents
import com.intellij.util.containers.map2Array
import net.earthcomputer.chasmintellij.ChasmIcons
import net.earthcomputer.chasmintellij.chasmElementFactory
import net.earthcomputer.chasmintellij.psi.*

fun getKey(entry: ChasmMapEntry): String {
    return entry.keyElement.text
}

fun getName(entry: ChasmMapEntry): String {
    return getKey(entry)
}

fun setName(entry: ChasmMapEntry, name: String): ChasmMapEntry {
    entry.keyElement.replace(entry.project.chasmElementFactory.createIdentifier(name))
    return entry
}

fun getReferenceName(reference: ChasmReferenceExpression): String {
    return reference.referenceElement.text
}

fun isGlobal(reference: ChasmReferenceExpression): Boolean {
    return reference.node.findChildByType(ChasmTypes.DOLLAR) != null
}

fun getReference(reference: ChasmReferenceExpression): PsiReference {
    return CachedValuesManager.getCachedValue(reference) {
        CachedValueProvider.Result(object : PsiReferenceBase<ChasmReferenceExpression>(reference, TextRange(0, reference.textLength)) {
            override fun resolve(): PsiElement? {
                return resolve(element)
            }

            override fun getVariants(): Array<Any> {
                return collectVariants(element)
            }

            override fun handleElementRename(newElementName: String): PsiElement {
                return handleElementRename(element, newElementName)
            }
        }, PsiModificationTracker.MODIFICATION_COUNT)
    }
}

private fun resolve(reference: ChasmReferenceExpression): PsiElement? {
    val name = reference.referenceName
    val sequence = reference.parents(false)
        .takeWhile { it !is ChasmFile }
        .mapNotNull { element ->
            when (element) {
                is ChasmMapExpression -> element.mapEntryList.firstOrNull { it.key == name }
                is ChasmLambdaExpression -> element.takeIf { it.argumentName == name }
                else -> null
            }
        }

    return if (reference.isGlobal) {
        sequence.lastOrNull()
    } else {
        sequence.firstOrNull()
    }
}

fun collectVariants(reference: ChasmReferenceExpression): Array<Any> {
    val variants = mutableMapOf<String, PsiElement>()
    val parents = reference.parents(false).takeWhile { it !is ChasmFile }.toMutableList()
    if (reference.isGlobal) {
        parents.reverse()
    }
    for (parent in parents) {
        when (parent) {
            is ChasmMapExpression -> {
                for (entry in parent.mapEntryList) {
                    variants.putIfAbsent(entry.key, entry)
                }
            }
            is ChasmLambdaExpression -> variants.putIfAbsent(parent.argumentName, parent)
        }
    }

    val prefix = if (reference.isGlobal) "$" else ""
    return variants.values.map2Array {
        LookupElementBuilder.create(it, prefix + (it as PsiNamedElement).name)
            .withIcon(ChasmIcons.FILE)
            .withTypeText(it.containingFile.name)
    }
}

fun handleElementRename(reference: ChasmReferenceExpression, name: String): ChasmReferenceExpression {
    reference.referenceElement.replace(reference.project.chasmElementFactory.createIdentifier(name))
    return reference
}

fun getArgumentName(lambda: ChasmLambdaExpression): String {
    return lambda.argumentElement.text
}

fun getName(lambda: ChasmLambdaExpression): String {
    return getArgumentName(lambda)
}

fun setName(lambda: ChasmLambdaExpression, name: String): ChasmLambdaExpression {
    lambda.argumentElement.replace(lambda.project.chasmElementFactory.createIdentifier(name))
    return lambda
}

fun getMemberName(memberExpr: ChasmMemberExpression): String {
    return memberExpr.memberNameElement.text
}

fun getOperator(unaryExpr: ChasmUnaryExpression): IElementType {
    return unaryExpr.node.firstChildNode.elementType
}

fun getReferences(literal: ChasmLiteralExpression): Array<PsiReference> {
    return PsiReferenceService.getService().getContributedReferences(literal)
}
