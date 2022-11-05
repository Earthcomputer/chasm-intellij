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

public class ChasmMultiplicativeExpressionImpl extends ChasmExpressionImpl implements ChasmMultiplicativeExpression {

  public ChasmMultiplicativeExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitMultiplicativeExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ChasmVisitor) accept((ChasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ChasmComment> getCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ChasmComment.class);
  }

  @Override
  @NotNull
  public List<ChasmDocComment> getDocCommentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ChasmDocComment.class);
  }

  @Override
  @NotNull
  public List<ChasmExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ChasmExpression.class);
  }

  @Override
  @NotNull
  public ChasmExpression getLeft() {
    List<ChasmExpression> p1 = getExpressionList();
    return p1.get(0);
  }

  @Override
  @Nullable
  public ChasmExpression getRight() {
    List<ChasmExpression> p1 = getExpressionList();
    return p1.size() < 2 ? null : p1.get(1);
  }

}
