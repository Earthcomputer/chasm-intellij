// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface ChasmMapEntry extends PsiNameIdentifierOwner {

  @NotNull
  ChasmIdentifier getKeyElement();

  @NotNull
  ChasmIdentifier getNameIdentifier();

  @NotNull
  String getKey();

  @NotNull
  String getName();

  @NotNull
  ChasmMapEntry setName(@NotNull String name);

  @Nullable
  ChasmExpression getValue();

}
