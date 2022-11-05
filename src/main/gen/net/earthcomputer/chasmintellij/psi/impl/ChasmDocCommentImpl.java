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
import com.intellij.psi.tree.IElementType;

public class ChasmDocCommentImpl extends ASTWrapperPsiElement implements ChasmDocComment {

  public ChasmDocCommentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ChasmVisitor visitor) {
    visitor.visitDocComment(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ChasmVisitor) accept((ChasmVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public IElementType getTokenType() {
    return ChasmPsiImplUtil.getTokenType(this);
  }

  @Override
  @Nullable
  public PsiElement getOwner() {
    return ChasmPsiImplUtil.getOwner(this);
  }

}
