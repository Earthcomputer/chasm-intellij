// This is a generated file. Not intended for manual editing.
package net.earthcomputer.chasmintellij;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static net.earthcomputer.chasmintellij.psi.ChasmTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ChasmParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return chasmFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADDITIVE_EXPRESSION, BITWISE_AND_EXPRESSION, BITWISE_OR_EXPRESSION, BITWISE_XOR_EXPRESSION,
      BOOLEAN_AND_EXPRESSION, BOOLEAN_OR_EXPRESSION, CALL_EXPRESSION, EQUALITY_EXPRESSION,
      EXPRESSION, INDEX_EXPRESSION, LAMBDA_EXPRESSION, LIST_EXPRESSION,
      LITERAL_EXPRESSION, MAP_EXPRESSION, MEMBER_EXPRESSION, MULTIPLICATIVE_EXPRESSION,
      PARENTHESES_EXPRESSION, REFERENCE_EXPRESSION, RELATIONAL_EXPRESSION, SHIFT_EXPRESSION,
      TERNARY_EXPRESSION, UNARY_EXPRESSION),
  };

  /* ********************************************************** */
  // skip (PLUS | MINUS) skip additivePrecedence
  public static boolean additiveExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, ADDITIVE_EXPRESSION, "<additive expression>");
    r = skip(b, l + 1);
    r = r && additiveExpression_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && additivePrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // PLUS | MINUS
  private static boolean additiveExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additiveExpression_1")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    return r;
  }

  /* ********************************************************** */
  // multiplicativePrecedence additiveExpression?
  static boolean additivePrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additivePrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = multiplicativePrecedence(b, l + 1);
    r = r && additivePrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // additiveExpression?
  private static boolean additivePrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "additivePrecedence_1")) return false;
    additiveExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (callExpression | indexExpression | memberExpression)*
  static boolean argumentExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentExpression")) return false;
    while (true) {
      int c = current_position_(b);
      if (!argumentExpression_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argumentExpression", c)) break;
    }
    return true;
  }

  // callExpression | indexExpression | memberExpression
  private static boolean argumentExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentExpression_0")) return false;
    boolean r;
    r = callExpression(b, l + 1);
    if (!r) r = indexExpression(b, l + 1);
    if (!r) r = memberExpression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // primaryExpression argumentExpression?
  static boolean argumentPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = primaryExpression(b, l + 1);
    r = r && argumentPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // argumentExpression?
  private static boolean argumentPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentPrecedence_1")) return false;
    argumentExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // skip BITWISE_AND skip bitwiseAndPrecedence
  public static boolean bitwiseAndExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseAndExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, BITWISE_AND_EXPRESSION, "<bitwise and expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, BITWISE_AND);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && bitwiseAndPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // equalityPrecedence bitwiseAndExpression?
  static boolean bitwiseAndPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseAndPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = equalityPrecedence(b, l + 1);
    r = r && bitwiseAndPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // bitwiseAndExpression?
  private static boolean bitwiseAndPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseAndPrecedence_1")) return false;
    bitwiseAndExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // skip BITWISE_OR skip bitwiseOrPrecedence
  public static boolean bitwiseOrExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseOrExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, BITWISE_OR_EXPRESSION, "<bitwise or expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, BITWISE_OR);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && bitwiseOrPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // bitwiseXorPrecedence bitwiseOrExpression?
  static boolean bitwiseOrPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseOrPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bitwiseXorPrecedence(b, l + 1);
    r = r && bitwiseOrPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // bitwiseOrExpression?
  private static boolean bitwiseOrPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseOrPrecedence_1")) return false;
    bitwiseOrExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // skip BITWISE_XOR skip bitwiseXorPrecedence
  public static boolean bitwiseXorExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseXorExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, BITWISE_XOR_EXPRESSION, "<bitwise xor expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, BITWISE_XOR);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && bitwiseXorPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // bitwiseAndPrecedence bitwiseXorExpression?
  static boolean bitwiseXorPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseXorPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bitwiseAndPrecedence(b, l + 1);
    r = r && bitwiseXorPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // bitwiseXorExpression?
  private static boolean bitwiseXorPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitwiseXorPrecedence_1")) return false;
    bitwiseXorExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // skip BOOL_AND skip booleanAndPrecedence
  public static boolean booleanAndExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAndExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, BOOLEAN_AND_EXPRESSION, "<boolean and expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, BOOL_AND);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && booleanAndPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // bitwiseOrPrecedence booleanAndExpression?
  static boolean booleanAndPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAndPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = bitwiseOrPrecedence(b, l + 1);
    r = r && booleanAndPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanAndExpression?
  private static boolean booleanAndPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanAndPrecedence_1")) return false;
    booleanAndExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // skip BOOL_OR skip booleanOrPrecedence
  public static boolean booleanOrExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanOrExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, BOOLEAN_OR_EXPRESSION, "<boolean or expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, BOOL_OR);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && booleanOrPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // booleanAndPrecedence booleanOrExpression?
  static boolean booleanOrPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanOrPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = booleanAndPrecedence(b, l + 1);
    r = r && booleanOrPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // booleanOrExpression?
  private static boolean booleanOrPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "booleanOrPrecedence_1")) return false;
    booleanOrExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // expression
  static boolean bracketsBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracketsBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = expression(b, l + 1);
    exit_section_(b, l, m, r, false, ChasmParser::bracketsRecover);
    return r;
  }

  /* ********************************************************** */
  // !(RPAREN | RBRACE | RBRACKET)
  static boolean bracketsRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracketsRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !bracketsRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // RPAREN | RBRACE | RBRACKET
  private static boolean bracketsRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bracketsRecover_0")) return false;
    boolean r;
    r = consumeToken(b, RPAREN);
    if (!r) r = consumeToken(b, RBRACE);
    if (!r) r = consumeToken(b, RBRACKET);
    return r;
  }

  /* ********************************************************** */
  // skip LPAREN skip bracketsBody skip RPAREN
  public static boolean callExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "callExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, CALL_EXPRESSION, "<call expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, bracketsBody(b, l + 1)) && r;
    r = p && report_error_(b, skip(b, l + 1)) && r;
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // skip expression skip <<eof>>
  static boolean chasmFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "chasmFile")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = skip(b, l + 1);
    r = r && expression(b, l + 1);
    r = r && skip(b, l + 1);
    r = r && eof(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LINE_COMMENT+ | (COMMENT_INLINE_START INLINE_COMMENT* COMMENT_INLINE_END )
  public static boolean comment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment")) return false;
    if (!nextTokenIs(b, "<comment>", COMMENT_INLINE_START, LINE_COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMENT, "<comment>");
    r = comment_0(b, l + 1);
    if (!r) r = comment_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LINE_COMMENT+
  private static boolean comment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LINE_COMMENT);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, LINE_COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "comment_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT_INLINE_START INLINE_COMMENT* COMMENT_INLINE_END
  private static boolean comment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT_INLINE_START);
    r = r && comment_1_1(b, l + 1);
    r = r && consumeToken(b, COMMENT_INLINE_END);
    exit_section_(b, m, null, r);
    return r;
  }

  // INLINE_COMMENT*
  private static boolean comment_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comment_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, INLINE_COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "comment_1_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DOC_COMMENT+ | (DOC_COMMENT_INLINE_START DOC_COMMENT* DOC_COMMENT_INLINE_END)
  public static boolean docComment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docComment")) return false;
    if (!nextTokenIs(b, "<doc comment>", DOC_COMMENT, DOC_COMMENT_INLINE_START)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOC_COMMENT_ELEMENT, "<doc comment>");
    r = docComment_0(b, l + 1);
    if (!r) r = docComment_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOC_COMMENT+
  private static boolean docComment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docComment_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOC_COMMENT);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, DOC_COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "docComment_0", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT_INLINE_START DOC_COMMENT* DOC_COMMENT_INLINE_END
  private static boolean docComment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docComment_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOC_COMMENT_INLINE_START);
    r = r && docComment_1_1(b, l + 1);
    r = r && consumeToken(b, DOC_COMMENT_INLINE_END);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOC_COMMENT*
  private static boolean docComment_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "docComment_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, DOC_COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "docComment_1_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // skip (EQUAL | NOT_EQUAL) skip equalityPrecedence
  public static boolean equalityExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, EQUALITY_EXPRESSION, "<equality expression>");
    r = skip(b, l + 1);
    r = r && equalityExpression_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && equalityPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // EQUAL | NOT_EQUAL
  private static boolean equalityExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityExpression_1")) return false;
    boolean r;
    r = consumeToken(b, EQUAL);
    if (!r) r = consumeToken(b, NOT_EQUAL);
    return r;
  }

  /* ********************************************************** */
  // relationalPrecedence equalityExpression?
  static boolean equalityPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = relationalPrecedence(b, l + 1);
    r = r && equalityPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // equalityExpression?
  private static boolean equalityPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "equalityPrecedence_1")) return false;
    equalityExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // lambdaExpression | ternaryPrecedence
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, EXPRESSION, "<expression>");
    r = lambdaExpression(b, l + 1);
    if (!r) r = ternaryPrecedence(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENT | (BACKTICK (ESCAPED_STRING | IDENT)* BACKTICK)
  public static boolean identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier")) return false;
    if (!nextTokenIs(b, "<identifier>", BACKTICK, IDENT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IDENTIFIER, "<identifier>");
    r = consumeToken(b, IDENT);
    if (!r) r = identifier_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BACKTICK (ESCAPED_STRING | IDENT)* BACKTICK
  private static boolean identifier_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BACKTICK);
    r = r && identifier_1_1(b, l + 1);
    r = r && consumeToken(b, BACKTICK);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ESCAPED_STRING | IDENT)*
  private static boolean identifier_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!identifier_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifier_1_1", c)) break;
    }
    return true;
  }

  // ESCAPED_STRING | IDENT
  private static boolean identifier_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_1_1_0")) return false;
    boolean r;
    r = consumeToken(b, ESCAPED_STRING);
    if (!r) r = consumeToken(b, IDENT);
    return r;
  }

  /* ********************************************************** */
  // skip LBRACKET skip bracketsBody skip RBRACKET
  public static boolean indexExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "indexExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, INDEX_EXPRESSION, "<index expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, LBRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, bracketsBody(b, l + 1)) && r;
    r = p && report_error_(b, skip(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // identifier skip LAMBDA skip expression
  public static boolean lambdaExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "lambdaExpression")) return false;
    if (!nextTokenIs(b, "<lambda expression>", BACKTICK, IDENT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LAMBDA_EXPRESSION, "<lambda expression>");
    r = identifier(b, l + 1);
    r = r && skip(b, l + 1);
    r = r && consumeToken(b, LAMBDA);
    p = r; // pin = 3
    r = r && report_error_(b, skip(b, l + 1));
    r = p && expression(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // expression
  static boolean listBracketsBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listBracketsBody")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = expression(b, l + 1);
    exit_section_(b, l, m, r, false, ChasmParser::listBracketsRecover);
    return r;
  }

  /* ********************************************************** */
  // !(RPAREN | RBRACE | RBRACKET | COMMA)
  static boolean listBracketsRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listBracketsRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !listBracketsRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // RPAREN | RBRACE | RBRACKET | COMMA
  private static boolean listBracketsRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listBracketsRecover_0")) return false;
    boolean r;
    r = consumeToken(b, RPAREN);
    if (!r) r = consumeToken(b, RBRACE);
    if (!r) r = consumeToken(b, RBRACKET);
    if (!r) r = consumeToken(b, COMMA);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET skip (listBracketsBody (skip COMMA skip listBracketsBody)* skip (COMMA skip)?)? RBRACKET
  public static boolean listExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LIST_EXPRESSION, null);
    r = consumeToken(b, LBRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, listExpression_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (listBracketsBody (skip COMMA skip listBracketsBody)* skip (COMMA skip)?)?
  private static boolean listExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression_2")) return false;
    listExpression_2_0(b, l + 1);
    return true;
  }

  // listBracketsBody (skip COMMA skip listBracketsBody)* skip (COMMA skip)?
  private static boolean listExpression_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = listBracketsBody(b, l + 1);
    r = r && listExpression_2_0_1(b, l + 1);
    r = r && skip(b, l + 1);
    r = r && listExpression_2_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (skip COMMA skip listBracketsBody)*
  private static boolean listExpression_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!listExpression_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "listExpression_2_0_1", c)) break;
    }
    return true;
  }

  // skip COMMA skip listBracketsBody
  private static boolean listExpression_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = skip(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && skip(b, l + 1);
    r = r && listBracketsBody(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA skip)?
  private static boolean listExpression_2_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression_2_0_3")) return false;
    listExpression_2_0_3_0(b, l + 1);
    return true;
  }

  // COMMA skip
  private static boolean listExpression_2_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "listExpression_2_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && skip(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NULL | BOOL | FLOAT | INTEGER | (QUOTE (UNESCAPED_STRING | ESCAPED_STRING)* QUOTE) | (SINGLE_QUOTE (UNESCAPED_STRING | ESCAPED_STRING) SINGLE_QUOTE)
  public static boolean literalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL_EXPRESSION, "<literal expression>");
    r = consumeToken(b, NULL);
    if (!r) r = consumeToken(b, BOOL);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = consumeToken(b, INTEGER);
    if (!r) r = literalExpression_4(b, l + 1);
    if (!r) r = literalExpression_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // QUOTE (UNESCAPED_STRING | ESCAPED_STRING)* QUOTE
  private static boolean literalExpression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, QUOTE);
    r = r && literalExpression_4_1(b, l + 1);
    r = r && consumeToken(b, QUOTE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (UNESCAPED_STRING | ESCAPED_STRING)*
  private static boolean literalExpression_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression_4_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!literalExpression_4_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "literalExpression_4_1", c)) break;
    }
    return true;
  }

  // UNESCAPED_STRING | ESCAPED_STRING
  private static boolean literalExpression_4_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression_4_1_0")) return false;
    boolean r;
    r = consumeToken(b, UNESCAPED_STRING);
    if (!r) r = consumeToken(b, ESCAPED_STRING);
    return r;
  }

  // SINGLE_QUOTE (UNESCAPED_STRING | ESCAPED_STRING) SINGLE_QUOTE
  private static boolean literalExpression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SINGLE_QUOTE);
    r = r && literalExpression_5_1(b, l + 1);
    r = r && consumeToken(b, SINGLE_QUOTE);
    exit_section_(b, m, null, r);
    return r;
  }

  // UNESCAPED_STRING | ESCAPED_STRING
  private static boolean literalExpression_5_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literalExpression_5_1")) return false;
    boolean r;
    r = consumeToken(b, UNESCAPED_STRING);
    if (!r) r = consumeToken(b, ESCAPED_STRING);
    return r;
  }

  /* ********************************************************** */
  // (literalExpression | identifier) skip COLON skip expression
  public static boolean mapEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapEntry")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_ENTRY, "<map entry>");
    r = mapEntry_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, skip(b, l + 1)) && r;
    r = p && expression(b, l + 1) && r;
    exit_section_(b, l, m, r, p, ChasmParser::mapEntryRecover);
    return r || p;
  }

  // literalExpression | identifier
  private static boolean mapEntry_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapEntry_0")) return false;
    boolean r;
    r = literalExpression(b, l + 1);
    if (!r) r = identifier(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // !(COMMA | RBRACE | RPAREN | RBRACKET)
  static boolean mapEntryRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapEntryRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !mapEntryRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMA | RBRACE | RPAREN | RBRACKET
  private static boolean mapEntryRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapEntryRecover_0")) return false;
    boolean r;
    r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, RBRACE);
    if (!r) r = consumeToken(b, RPAREN);
    if (!r) r = consumeToken(b, RBRACKET);
    return r;
  }

  /* ********************************************************** */
  // LBRACE skip (mapEntry (skip COMMA skip mapEntry)* skip (COMMA skip)?)? RBRACE
  public static boolean mapExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, MAP_EXPRESSION, null);
    r = consumeToken(b, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, mapExpression_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (mapEntry (skip COMMA skip mapEntry)* skip (COMMA skip)?)?
  private static boolean mapExpression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression_2")) return false;
    mapExpression_2_0(b, l + 1);
    return true;
  }

  // mapEntry (skip COMMA skip mapEntry)* skip (COMMA skip)?
  private static boolean mapExpression_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = mapEntry(b, l + 1);
    r = r && mapExpression_2_0_1(b, l + 1);
    r = r && skip(b, l + 1);
    r = r && mapExpression_2_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (skip COMMA skip mapEntry)*
  private static boolean mapExpression_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!mapExpression_2_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "mapExpression_2_0_1", c)) break;
    }
    return true;
  }

  // skip COMMA skip mapEntry
  private static boolean mapExpression_2_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression_2_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = skip(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && skip(b, l + 1);
    r = r && mapEntry(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA skip)?
  private static boolean mapExpression_2_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression_2_0_3")) return false;
    mapExpression_2_0_3_0(b, l + 1);
    return true;
  }

  // COMMA skip
  private static boolean mapExpression_2_0_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapExpression_2_0_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && skip(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // skip DOT skip identifier
  public static boolean memberExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "memberExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, MEMBER_EXPRESSION, "<member expression>");
    r = skip(b, l + 1);
    r = r && consumeToken(b, DOT);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && identifier(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // skip (MULTIPLY | DIVIDE | MODULO) skip multiplicativePrecedence
  public static boolean multiplicativeExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, MULTIPLICATIVE_EXPRESSION, "<multiplicative expression>");
    r = skip(b, l + 1);
    r = r && multiplicativeExpression_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && multiplicativePrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // MULTIPLY | DIVIDE | MODULO
  private static boolean multiplicativeExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativeExpression_1")) return false;
    boolean r;
    r = consumeToken(b, MULTIPLY);
    if (!r) r = consumeToken(b, DIVIDE);
    if (!r) r = consumeToken(b, MODULO);
    return r;
  }

  /* ********************************************************** */
  // unaryPrecedence multiplicativeExpression?
  static boolean multiplicativePrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativePrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unaryPrecedence(b, l + 1);
    r = r && multiplicativePrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // multiplicativeExpression?
  private static boolean multiplicativePrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "multiplicativePrecedence_1")) return false;
    multiplicativeExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LPAREN skip bracketsBody skip RPAREN
  public static boolean parenthesesExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesesExpression")) return false;
    if (!nextTokenIs(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESES_EXPRESSION, null);
    r = consumeToken(b, LPAREN);
    p = r; // pin = 1
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, bracketsBody(b, l + 1)) && r;
    r = p && report_error_(b, skip(b, l + 1)) && r;
    r = p && consumeToken(b, RPAREN) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // literalExpression | referenceExpression | parenthesesExpression | listExpression | mapExpression
  static boolean primaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "primaryExpression")) return false;
    boolean r;
    r = literalExpression(b, l + 1);
    if (!r) r = referenceExpression(b, l + 1);
    if (!r) r = parenthesesExpression(b, l + 1);
    if (!r) r = listExpression(b, l + 1);
    if (!r) r = mapExpression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // DOLLAR? identifier
  public static boolean referenceExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REFERENCE_EXPRESSION, "<reference expression>");
    r = referenceExpression_0(b, l + 1);
    r = r && identifier(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DOLLAR?
  private static boolean referenceExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "referenceExpression_0")) return false;
    consumeToken(b, DOLLAR);
    return true;
  }

  /* ********************************************************** */
  // skip (LESS_THAN | LESS_THAN_EQUAL | GREATER_THAN | GREATER_THAN_EQUAL) skip relationalPrecedence
  public static boolean relationalExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, RELATIONAL_EXPRESSION, "<relational expression>");
    r = skip(b, l + 1);
    r = r && relationalExpression_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && relationalPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // LESS_THAN | LESS_THAN_EQUAL | GREATER_THAN | GREATER_THAN_EQUAL
  private static boolean relationalExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalExpression_1")) return false;
    boolean r;
    r = consumeToken(b, LESS_THAN);
    if (!r) r = consumeToken(b, LESS_THAN_EQUAL);
    if (!r) r = consumeToken(b, GREATER_THAN);
    if (!r) r = consumeToken(b, GREATER_THAN_EQUAL);
    return r;
  }

  /* ********************************************************** */
  // shiftPrecedence relationalExpression?
  static boolean relationalPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = shiftPrecedence(b, l + 1);
    r = r && relationalPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // relationalExpression?
  private static boolean relationalPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationalPrecedence_1")) return false;
    relationalExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // skip (SHIFT_LEFT | SHIFT_RIGHT | SHIFT_RIGHT_UNSIGNED) skip shiftPrecedence
  public static boolean shiftExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _LEFT_, SHIFT_EXPRESSION, "<shift expression>");
    r = skip(b, l + 1);
    r = r && shiftExpression_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, skip(b, l + 1));
    r = p && shiftPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // SHIFT_LEFT | SHIFT_RIGHT | SHIFT_RIGHT_UNSIGNED
  private static boolean shiftExpression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftExpression_1")) return false;
    boolean r;
    r = consumeToken(b, SHIFT_LEFT);
    if (!r) r = consumeToken(b, SHIFT_RIGHT);
    if (!r) r = consumeToken(b, SHIFT_RIGHT_UNSIGNED);
    return r;
  }

  /* ********************************************************** */
  // additivePrecedence shiftExpression?
  static boolean shiftPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftPrecedence")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = additivePrecedence(b, l + 1);
    r = r && shiftPrecedence_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // shiftExpression?
  private static boolean shiftPrecedence_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "shiftPrecedence_1")) return false;
    shiftExpression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (SKIP | comment | docComment)*
  static boolean skip(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "skip")) return false;
    while (true) {
      int c = current_position_(b);
      if (!skip_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "skip", c)) break;
    }
    return true;
  }

  // SKIP | comment | docComment
  private static boolean skip_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "skip_0")) return false;
    boolean r;
    r = consumeToken(b, SKIP);
    if (!r) r = comment(b, l + 1);
    if (!r) r = docComment(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // booleanOrPrecedence skip TERNARY skip ternaryPrecedence skip COLON skip ternaryPrecedence
  public static boolean ternaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternaryExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _COLLAPSE_, TERNARY_EXPRESSION, "<ternary expression>");
    r = booleanOrPrecedence(b, l + 1);
    r = r && skip(b, l + 1);
    r = r && consumeToken(b, TERNARY);
    p = r; // pin = 3
    r = r && report_error_(b, skip(b, l + 1));
    r = p && report_error_(b, ternaryPrecedence(b, l + 1)) && r;
    r = p && report_error_(b, skip(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && report_error_(b, skip(b, l + 1)) && r;
    r = p && ternaryPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ternaryExpression | booleanOrPrecedence
  static boolean ternaryPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ternaryPrecedence")) return false;
    boolean r;
    r = ternaryExpression(b, l + 1);
    if (!r) r = booleanOrPrecedence(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (PLUS | MINUS | NOT | INVERT) skip unaryPrecedence
  public static boolean unaryExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _COLLAPSE_, UNARY_EXPRESSION, "<unary expression>");
    r = unaryExpression_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, skip(b, l + 1));
    r = p && unaryPrecedence(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // PLUS | MINUS | NOT | INVERT
  private static boolean unaryExpression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryExpression_0")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, INVERT);
    return r;
  }

  /* ********************************************************** */
  // unaryExpression | argumentPrecedence
  static boolean unaryPrecedence(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryPrecedence")) return false;
    boolean r;
    r = unaryExpression(b, l + 1);
    if (!r) r = argumentPrecedence(b, l + 1);
    return r;
  }

}
