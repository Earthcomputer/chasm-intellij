// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.earthcomputer.chasmintellij.psi.ChasmTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.earthcomputer.chasmintellij.psi.*;

public class ChasmMapEntryImpl extends ASTWrapperPsiElement implements ChasmMapEntry {

  public ChasmMapEntryImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitMapEntry(this);
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
  @Nullable
  public ChasmIdentifier getKeyIdentifier() {
    return findChildByClass(ChasmIdentifier.class);
  }

  @Override
  @Nullable
  public ChasmLiteralExpression getKeyLiteral() {
    return ChasmPsiImplUtil.getKeyLiteral(this);
  }

  @Override
  @NotNull
  public PsiElement getKeyElement() {
    return ChasmPsiImplUtil.getKeyElement(this);
  }

  @Override
  @Nullable
  public ChasmIdentifier getNameIdentifier() {
    return getKeyIdentifier();
  }

  @Override
  @Nullable
  public String getKey() {
    return ChasmPsiImplUtil.getKey(this);
  }

  @Override
  @Nullable
  public String getName() {
    return ChasmPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public ChasmMapEntry setName(@NotNull String name) {
    return ChasmPsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public ChasmExpression getValue() {
    return ChasmPsiImplUtil.getValue(this);
  }

}
