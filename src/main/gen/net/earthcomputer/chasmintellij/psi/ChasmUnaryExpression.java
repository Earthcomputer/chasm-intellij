// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

public interface ChasmUnaryExpression extends ChasmExpression {

  @NotNull
  List<ChasmComment> getCommentList();

  @NotNull
  List<ChasmDocComment> getDocCommentList();

  @NotNull
  IElementType getOperator();

  @Nullable
  ChasmExpression getOperand();

}
