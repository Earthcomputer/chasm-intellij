// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface ChasmLambdaExpression extends ChasmExpression, PsiNameIdentifierOwner {

  @NotNull
  List<ChasmComment> getCommentList();

  @NotNull
  List<ChasmDocComment> getDocCommentList();

  @NotNull
  ChasmIdentifier getArgumentElement();

  @NotNull
  String getArgumentName();

  @NotNull
  ChasmIdentifier getNameIdentifier();

  @NotNull
  String getName();

  @NotNull
  ChasmLambdaExpression setName(@NotNull String name);

  @Nullable
  ChasmExpression getBody();

}
