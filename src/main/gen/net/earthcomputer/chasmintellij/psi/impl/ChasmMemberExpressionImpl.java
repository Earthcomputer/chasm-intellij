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

public class ChasmMemberExpressionImpl extends ChasmExpressionImpl implements ChasmMemberExpression {

  public ChasmMemberExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitMemberExpression(this);
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
  public ChasmExpression getExpression() {
    return findNotNullChildByClass(ChasmExpression.class);
  }

  @Override
  @Nullable
  public ChasmIdentifier getMemberNameElement() {
    return findChildByClass(ChasmIdentifier.class);
  }

  @Override
  @Nullable
  public String getMemberName() {
    return ChasmPsiImplUtil.getMemberName(this);
  }

  @Override
  @Nullable
  public PsiReference getReference() {
    return ChasmPsiImplUtil.getReference(this);
  }

}
