// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import net.earthcomputer.chasmintellij.psi.impl.*;

public interface ChasmTypes {

  IElementType ADDITIVE_EXPRESSION = new ChasmElementType("ADDITIVE_EXPRESSION");
  IElementType BITWISE_AND_EXPRESSION = new ChasmElementType("BITWISE_AND_EXPRESSION");
  IElementType BITWISE_OR_EXPRESSION = new ChasmElementType("BITWISE_OR_EXPRESSION");
  IElementType BITWISE_XOR_EXPRESSION = new ChasmElementType("BITWISE_XOR_EXPRESSION");
  IElementType BOOLEAN_AND_EXPRESSION = new ChasmElementType("BOOLEAN_AND_EXPRESSION");
  IElementType BOOLEAN_OR_EXPRESSION = new ChasmElementType("BOOLEAN_OR_EXPRESSION");
  IElementType CALL_EXPRESSION = new ChasmElementType("CALL_EXPRESSION");
  IElementType EQUALITY_EXPRESSION = new ChasmElementType("EQUALITY_EXPRESSION");
  IElementType EXPRESSION = new ChasmElementType("EXPRESSION");
  IElementType IDENTIFIER = new ChasmElementType("IDENTIFIER");
  IElementType INDEX_EXPRESSION = new ChasmElementType("INDEX_EXPRESSION");
  IElementType LAMBDA_EXPRESSION = new ChasmElementType("LAMBDA_EXPRESSION");
  IElementType LIST_EXPRESSION = new ChasmElementType("LIST_EXPRESSION");
  IElementType LITERAL_EXPRESSION = new ChasmElementType("LITERAL_EXPRESSION");
  IElementType MAP_ENTRY = new ChasmElementType("MAP_ENTRY");
  IElementType MAP_EXPRESSION = new ChasmElementType("MAP_EXPRESSION");
  IElementType MEMBER_EXPRESSION = new ChasmElementType("MEMBER_EXPRESSION");
  IElementType MULTIPLICATIVE_EXPRESSION = new ChasmElementType("MULTIPLICATIVE_EXPRESSION");
  IElementType PARENTHESES_EXPRESSION = new ChasmElementType("PARENTHESES_EXPRESSION");
  IElementType REFERENCE_EXPRESSION = new ChasmElementType("REFERENCE_EXPRESSION");
  IElementType RELATIONAL_EXPRESSION = new ChasmElementType("RELATIONAL_EXPRESSION");
  IElementType SHIFT_EXPRESSION = new ChasmElementType("SHIFT_EXPRESSION");
  IElementType TERNARY_EXPRESSION = new ChasmElementType("TERNARY_EXPRESSION");
  IElementType UNARY_EXPRESSION = new ChasmElementType("UNARY_EXPRESSION");

  IElementType BACKTICK = new ChasmTokenType("BACKTICK");
  IElementType BITWISE_AND = new ChasmTokenType("BITWISE_AND");
  IElementType BITWISE_OR = new ChasmTokenType("BITWISE_OR");
  IElementType BITWISE_XOR = new ChasmTokenType("BITWISE_XOR");
  IElementType BOOL = new ChasmTokenType("BOOL");
  IElementType BOOL_AND = new ChasmTokenType("BOOL_AND");
  IElementType BOOL_OR = new ChasmTokenType("BOOL_OR");
  IElementType COLON = new ChasmTokenType("COLON");
  IElementType COMMA = new ChasmTokenType("COMMA");
  IElementType DIVIDE = new ChasmTokenType("DIVIDE");
  IElementType DOLLAR = new ChasmTokenType("DOLLAR");
  IElementType DOT = new ChasmTokenType("DOT");
  IElementType EQUAL = new ChasmTokenType("EQUAL");
  IElementType ESCAPED_STRING = new ChasmTokenType("ESCAPED_STRING");
  IElementType FLOAT = new ChasmTokenType("FLOAT");
  IElementType GREATER_THAN = new ChasmTokenType("GREATER_THAN");
  IElementType GREATER_THAN_EQUAL = new ChasmTokenType("GREATER_THAN_EQUAL");
  IElementType IDENT = new ChasmTokenType("IDENT");
  IElementType INTEGER = new ChasmTokenType("INTEGER");
  IElementType INVERT = new ChasmTokenType("INVERT");
  IElementType LAMBDA = new ChasmTokenType("LAMBDA");
  IElementType LBRACE = new ChasmTokenType("LBRACE");
  IElementType LBRACKET = new ChasmTokenType("LBRACKET");
  IElementType LESS_THAN = new ChasmTokenType("LESS_THAN");
  IElementType LESS_THAN_EQUAL = new ChasmTokenType("LESS_THAN_EQUAL");
  IElementType LPAREN = new ChasmTokenType("LPAREN");
  IElementType MINUS = new ChasmTokenType("MINUS");
  IElementType MODULO = new ChasmTokenType("MODULO");
  IElementType MULTIPLY = new ChasmTokenType("MULTIPLY");
  IElementType NOT = new ChasmTokenType("NOT");
  IElementType NOT_EQUAL = new ChasmTokenType("NOT_EQUAL");
  IElementType NULL = new ChasmTokenType("NULL");
  IElementType PLUS = new ChasmTokenType("PLUS");
  IElementType QUOTE = new ChasmTokenType("QUOTE");
  IElementType RBRACE = new ChasmTokenType("RBRACE");
  IElementType RBRACKET = new ChasmTokenType("RBRACKET");
  IElementType RPAREN = new ChasmTokenType("RPAREN");
  IElementType SHIFT_LEFT = new ChasmTokenType("SHIFT_LEFT");
  IElementType SHIFT_RIGHT = new ChasmTokenType("SHIFT_RIGHT");
  IElementType SHIFT_RIGHT_UNSIGNED = new ChasmTokenType("SHIFT_RIGHT_UNSIGNED");
  IElementType SINGLE_QUOTE = new ChasmTokenType("SINGLE_QUOTE");
  IElementType SKIP = new ChasmTokenType("SKIP");
  IElementType TERNARY = new ChasmTokenType("TERNARY");
  IElementType UNESCAPED_STRING = new ChasmTokenType("UNESCAPED_STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADDITIVE_EXPRESSION) {
        return new ChasmAdditiveExpressionImpl(node);
      }
      else if (type == BITWISE_AND_EXPRESSION) {
        return new ChasmBitwiseAndExpressionImpl(node);
      }
      else if (type == BITWISE_OR_EXPRESSION) {
        return new ChasmBitwiseOrExpressionImpl(node);
      }
      else if (type == BITWISE_XOR_EXPRESSION) {
        return new ChasmBitwiseXorExpressionImpl(node);
      }
      else if (type == BOOLEAN_AND_EXPRESSION) {
        return new ChasmBooleanAndExpressionImpl(node);
      }
      else if (type == BOOLEAN_OR_EXPRESSION) {
        return new ChasmBooleanOrExpressionImpl(node);
      }
      else if (type == CALL_EXPRESSION) {
        return new ChasmCallExpressionImpl(node);
      }
      else if (type == EQUALITY_EXPRESSION) {
        return new ChasmEqualityExpressionImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new ChasmIdentifierImpl(node);
      }
      else if (type == INDEX_EXPRESSION) {
        return new ChasmIndexExpressionImpl(node);
      }
      else if (type == LAMBDA_EXPRESSION) {
        return new ChasmLambdaExpressionImpl(node);
      }
      else if (type == LIST_EXPRESSION) {
        return new ChasmListExpressionImpl(node);
      }
      else if (type == LITERAL_EXPRESSION) {
        return new ChasmLiteralExpressionImpl(node);
      }
      else if (type == MAP_ENTRY) {
        return new ChasmMapEntryImpl(node);
      }
      else if (type == MAP_EXPRESSION) {
        return new ChasmMapExpressionImpl(node);
      }
      else if (type == MEMBER_EXPRESSION) {
        return new ChasmMemberExpressionImpl(node);
      }
      else if (type == MULTIPLICATIVE_EXPRESSION) {
        return new ChasmMultiplicativeExpressionImpl(node);
      }
      else if (type == PARENTHESES_EXPRESSION) {
        return new ChasmParenthesesExpressionImpl(node);
      }
      else if (type == REFERENCE_EXPRESSION) {
        return new ChasmReferenceExpressionImpl(node);
      }
      else if (type == RELATIONAL_EXPRESSION) {
        return new ChasmRelationalExpressionImpl(node);
      }
      else if (type == SHIFT_EXPRESSION) {
        return new ChasmShiftExpressionImpl(node);
      }
      else if (type == TERNARY_EXPRESSION) {
        return new ChasmTernaryExpressionImpl(node);
      }
      else if (type == UNARY_EXPRESSION) {
        return new ChasmUnaryExpressionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
