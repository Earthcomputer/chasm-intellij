// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ChasmBitwiseAndExpression extends ChasmExpression, ChasmBinaryExpression {

  @NotNull
  List<ChasmExpression> getExpressionList();

  @NotNull
  ChasmExpression getLeft();

  @Nullable
  ChasmExpression getRight();

}
