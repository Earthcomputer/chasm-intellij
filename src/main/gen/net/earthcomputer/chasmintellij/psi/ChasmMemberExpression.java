// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ChasmMemberExpression extends ChasmExpression {

  @NotNull
  ChasmExpression getExpression();

  @Nullable
  ChasmIdentifier getMemberNameElement();

  @Nullable
  String getMemberName();

  @Nullable
  PsiReference getReference();

}
