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

public class ChasmLambdaExpressionImpl extends ChasmExpressionImpl implements ChasmLambdaExpression {

  public ChasmLambdaExpressionImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitLambdaExpression(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ChasmVisitor) accept((ChasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ChasmIdentifier getArgumentElement() {
    return findNotNullChildByClass(ChasmIdentifier.class);
  }

  @Override
  @NotNull
  public String getArgumentName() {
    return ChasmPsiImplUtil.getArgumentName(this);
  }

  @Override
  @NotNull
  public ChasmIdentifier getNameIdentifier() {
    return getArgumentElement();
  }

  @Override
  @NotNull
  public String getName() {
    return ChasmPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public ChasmLambdaExpression setName(@NotNull String name) {
    return ChasmPsiImplUtil.setName(this, name);
  }

  @Override
  @NotNull
  public ChasmExpression getBody() {
    return findNotNullChildByClass(ChasmExpression.class);
  }

}
