// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.earthcomputer.chasmintellij.psi.ChasmTypes.*;
import net.earthcomputer.chasmintellij.psi.*;
import com.intellij.psi.PsiReference;

public class ChasmReferenceExpressionImpl extends ChasmExpressionImpl implements ChasmReferenceExpression {

  public ChasmReferenceExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitReferenceExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ChasmVisitor) accept((ChasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ChasmIdentifier getReferenceElement() {
    return findNotNullChildByClass(ChasmIdentifier.class);
  }

  @Override
  @NotNull
  public String getReferenceName() {
    return ChasmPsiImplUtil.getReferenceName(this);
  }

  @Override
  @NotNull
  public PsiReference getReference() {
    return ChasmPsiImplUtil.getReference(this);
  }

  @Override
  public boolean isGlobal() {
    return ChasmPsiImplUtil.isGlobal(this);
  }

}
