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

public class ChasmLiteralExpressionImpl extends ChasmExpressionImpl implements ChasmLiteralExpression {

  public ChasmLiteralExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitLiteralExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ChasmVisitor) accept((ChasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiReference[] getReferences() {
    return ChasmPsiImplUtil.getReferences(this);
  }

}
