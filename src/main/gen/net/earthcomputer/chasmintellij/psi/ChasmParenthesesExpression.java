// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ChasmParenthesesExpression extends ChasmExpression {

  @NotNull
  List<ChasmComment> getCommentList();

  @NotNull
  List<ChasmDocComment> getDocCommentList();

  @Nullable
  ChasmExpression getExpression();

}
