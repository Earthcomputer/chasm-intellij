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
  public ChasmExpression getExpression() {
    return findNotNullChildByClass(ChasmExpression.class);
  }

  @Override
  @NotNull
  public ChasmIdentifier getMemberNameElement() {
    return findNotNullChildByClass(ChasmIdentifier.class);
  }

  @Override
  @NotNull
  public String getMemberName() {
    return ChasmPsiImplUtil.getMemberName(this);
  }

}
