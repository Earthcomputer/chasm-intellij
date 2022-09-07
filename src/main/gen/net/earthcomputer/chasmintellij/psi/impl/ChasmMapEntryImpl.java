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
  public ChasmIdentifier getKeyElement() {
    return findNotNullChildByClass(ChasmIdentifier.class);
  }

  @Override
  @NotNull
  public ChasmIdentifier getNameIdentifier() {
    return getKeyElement();
  }

  @Override
  @NotNull
  public String getKey() {
    return ChasmPsiImplUtil.getKey(this);
  }

  @Override
  @NotNull
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
    return findChildByClass(ChasmExpression.class);
  }

}
