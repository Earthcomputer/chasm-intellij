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

public class ChasmListExpressionImpl extends ChasmExpressionImpl implements ChasmListExpression {

  public ChasmListExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitListExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ChasmVisitor) accept((ChasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ChasmExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ChasmExpression.class);
  }

}
