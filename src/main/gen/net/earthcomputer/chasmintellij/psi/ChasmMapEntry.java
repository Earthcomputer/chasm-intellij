// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface ChasmMapEntry extends PsiNameIdentifierOwner {

  @NotNull
  List<ChasmComment> getCommentList();

  @NotNull
  List<ChasmDocComment> getDocCommentList();

  @NotNull
  List<ChasmExpression> getExpressionList();

  @Nullable
  ChasmIdentifier getKeyIdentifier();

  @Nullable
  ChasmLiteralExpression getKeyLiteral();

  @NotNull
  PsiElement getKeyElement();

  @Nullable
  ChasmIdentifier getNameIdentifier();

  @Nullable
  String getKey();

  @Nullable
  String getName();

  @NotNull
  ChasmMapEntry setName(@NotNull String name);

  @Nullable
  ChasmExpression getValue();

}
