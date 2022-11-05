// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ChasmAdditiveExpression extends ChasmExpression, ChasmBinaryExpression {

  @NotNull
  List<ChasmComment> getCommentList();

  @NotNull
  List<ChasmDocComment> getDocCommentList();

  @NotNull
  List<ChasmExpression> getExpressionList();

  @NotNull
  ChasmExpression getLeft();

  @Nullable
  ChasmExpression getRight();

}
