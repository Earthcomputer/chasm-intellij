// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiDocCommentBase;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiComment;
import com.intellij.psi.ContributedReferenceHost;

public class ChasmVisitor extends PsiElementVisitor {

  public void visitAdditiveExpression(@NotNull ChasmAdditiveExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitBitwiseAndExpression(@NotNull ChasmBitwiseAndExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitBitwiseOrExpression(@NotNull ChasmBitwiseOrExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitBitwiseXorExpression(@NotNull ChasmBitwiseXorExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitBooleanAndExpression(@NotNull ChasmBooleanAndExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitBooleanOrExpression(@NotNull ChasmBooleanOrExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitCallExpression(@NotNull ChasmCallExpression o) {
    visitExpression(o);
  }

  public void visitComment(@NotNull ChasmComment o) {
    visitPsiComment(o);
  }

  public void visitDocComment(@NotNull ChasmDocComment o) {
    visitPsiDocCommentBase(o);
  }

  public void visitEqualityExpression(@NotNull ChasmEqualityExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitExpression(@NotNull ChasmExpression o) {
    visitPsiElement(o);
  }

  public void visitIdentifier(@NotNull ChasmIdentifier o) {
    visitPsiElement(o);
  }

  public void visitIndexExpression(@NotNull ChasmIndexExpression o) {
    visitExpression(o);
  }

  public void visitLambdaExpression(@NotNull ChasmLambdaExpression o) {
    visitExpression(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitListExpression(@NotNull ChasmListExpression o) {
    visitExpression(o);
  }

  public void visitLiteralExpression(@NotNull ChasmLiteralExpression o) {
    visitExpression(o);
    // visitContributedReferenceHost(o);
  }

  public void visitMapEntry(@NotNull ChasmMapEntry o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitMapExpression(@NotNull ChasmMapExpression o) {
    visitExpression(o);
  }

  public void visitMemberExpression(@NotNull ChasmMemberExpression o) {
    visitExpression(o);
  }

  public void visitMultiplicativeExpression(@NotNull ChasmMultiplicativeExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitParenthesesExpression(@NotNull ChasmParenthesesExpression o) {
    visitExpression(o);
  }

  public void visitReferenceExpression(@NotNull ChasmReferenceExpression o) {
    visitExpression(o);
  }

  public void visitRelationalExpression(@NotNull ChasmRelationalExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitShiftExpression(@NotNull ChasmShiftExpression o) {
    visitExpression(o);
    // visitBinaryExpression(o);
  }

  public void visitTernaryExpression(@NotNull ChasmTernaryExpression o) {
    visitExpression(o);
  }

  public void visitUnaryExpression(@NotNull ChasmUnaryExpression o) {
    visitExpression(o);
  }

  public void visitPsiComment(@NotNull PsiComment o) {
    visitElement(o);
  }

  public void visitPsiDocCommentBase(@NotNull PsiDocCommentBase o) {
    visitElement(o);
  }

  public void visitPsiNameIdentifierOwner(@NotNull PsiNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
