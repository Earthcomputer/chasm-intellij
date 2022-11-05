package net.earthcomputer.chasmintellij;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import net.earthcomputer.chasmintellij.psi.ChasmTypes;

%%

%class ChasmLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

SKIP=[ \n\r\t]+

BEGIN_LINE_COMMENT="//"
BEGIN_INLINE_COMMENT="/*"
END_INLINE_COMMENT="*/"
BEGIN_LINE_DOC_COMMENT="/**"
BEGIN_INLINE_DOC_COMMENT="///"


NULL="null"
BOOL="true" | "false"
FLOAT=("+" | "-")? [0-9]+ "." [0-9]+ ("e" ("+" | "-")? [0-9]+)?
INTEGER=("0x" [0-9a-fA-F]+) | ("0b" [0-1]+) | (("+" | "-")? [0-9]+)

IDENT=[_a-zA-Z] [_a-zA-Z0-9]*

PLUS="+"
MINUS="-"
NOT="!"
INVERT="~"
MULTIPLY="*"
DIVIDE="/"
MODULO="%"
SHIFT_LEFT="<<"
SHIFT_RIGHT=">>"
SHIFT_RIGHT_UNSIGNED=">>>"
LESS_THAN="<"
LESS_THAN_EQUAL="<="
GREATER_THAN=">"
GREATER_THAN_EQUAL=">="
EQUAL="="
NOT_EQUAL="!="
BITWISE_AND="&"
BITWISE_XOR="^"
BITWISE_OR="|"
BOOL_AND="&&"
BOOL_OR="||"
TERNARY="?"
LAMBDA="->"

DOT="."
COMMA=","
COLON=":"
LPAREN="("
RPAREN=")"
LBRACKET="["
RBRACKET="]"
LBRACE="{"
RBRACE="}"
DOLLAR="$"
QUOTE=\"
SINGLE_QUOTE='
BACKTICK=`

%state WAITING_CLOSE_QUOTE
%state WAITING_CLOSE_SINGLE_QUOTE
%state WAITING_CLOSE_BACKTICK
%state INSIDE_LINE_DOC_COMMENT
%state INSIDE_INLINE_DOC_COMMENT
%state INSIDE_LINE_COMMENT
%state INSIDE_INLINE_COMMENT

%%

<YYINITIAL> {SKIP} { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<YYINITIAL> {BEGIN_LINE_DOC_COMMENT} { yybegin(INSIDE_LINE_DOC_COMMENT); return ChasmTypes.DOC_COMMENT; }
<YYINITIAL> {BEGIN_INLINE_DOC_COMMENT} { yybegin(INSIDE_INLINE_DOC_COMMENT); return ChasmTypes.DOC_COMMENT_INLINE_START; }
<YYINITIAL> {BEGIN_LINE_COMMENT} { yybegin(INSIDE_LINE_COMMENT); return ChasmTypes.LINE_COMMENT; }
<YYINITIAL> {BEGIN_INLINE_COMMENT} { yybegin(INSIDE_INLINE_COMMENT); return ChasmTypes.COMMENT_INLINE_START; }
<YYINITIAL> {NULL} { yybegin(YYINITIAL); return ChasmTypes.NULL; }
<YYINITIAL> {BOOL} { yybegin(YYINITIAL); return ChasmTypes.BOOL; }
<YYINITIAL> {FLOAT} { yybegin(YYINITIAL); return ChasmTypes.FLOAT; }
<YYINITIAL> {INTEGER} { yybegin(YYINITIAL); return ChasmTypes.INTEGER; }
<YYINITIAL> {IDENT} { yybegin(YYINITIAL); return ChasmTypes.IDENT; }
<YYINITIAL> {PLUS} { yybegin(YYINITIAL); return ChasmTypes.PLUS; }
<YYINITIAL> {MINUS} { yybegin(YYINITIAL); return ChasmTypes.MINUS; }
<YYINITIAL> {NOT} { yybegin(YYINITIAL); return ChasmTypes.NOT; }
<YYINITIAL> {INVERT} { yybegin(YYINITIAL); return ChasmTypes.INVERT; }
<YYINITIAL> {MULTIPLY} { yybegin(YYINITIAL); return ChasmTypes.MULTIPLY; }
<YYINITIAL> {DIVIDE} { yybegin(YYINITIAL); return ChasmTypes.DIVIDE; }
<YYINITIAL> {MODULO} { yybegin(YYINITIAL); return ChasmTypes.MODULO; }
<YYINITIAL> {SHIFT_LEFT} { yybegin(YYINITIAL); return ChasmTypes.SHIFT_LEFT; }
<YYINITIAL> {SHIFT_RIGHT} { yybegin(YYINITIAL); return ChasmTypes.SHIFT_RIGHT; }
<YYINITIAL> {SHIFT_RIGHT_UNSIGNED} { yybegin(YYINITIAL); return ChasmTypes.SHIFT_RIGHT_UNSIGNED; }
<YYINITIAL> {LESS_THAN} { yybegin(YYINITIAL); return ChasmTypes.LESS_THAN; }
<YYINITIAL> {LESS_THAN_EQUAL} { yybegin(YYINITIAL); return ChasmTypes.LESS_THAN_EQUAL; }
<YYINITIAL> {GREATER_THAN} { yybegin(YYINITIAL); return ChasmTypes.GREATER_THAN; }
<YYINITIAL> {GREATER_THAN_EQUAL} { yybegin(YYINITIAL); return ChasmTypes.GREATER_THAN_EQUAL; }
<YYINITIAL> {EQUAL} { yybegin(YYINITIAL); return ChasmTypes.EQUAL; }
<YYINITIAL> {NOT_EQUAL} { yybegin(YYINITIAL); return ChasmTypes.NOT_EQUAL; }
<YYINITIAL> {BITWISE_AND} { yybegin(YYINITIAL); return ChasmTypes.BITWISE_AND; }
<YYINITIAL> {BITWISE_XOR} { yybegin(YYINITIAL); return ChasmTypes.BITWISE_XOR; }
<YYINITIAL> {BITWISE_OR} { yybegin(YYINITIAL); return ChasmTypes.BITWISE_OR; }
<YYINITIAL> {BOOL_AND} { yybegin(YYINITIAL); return ChasmTypes.BOOL_AND; }
<YYINITIAL> {BOOL_OR} { yybegin(YYINITIAL); return ChasmTypes.BOOL_OR; }
<YYINITIAL> {TERNARY} { yybegin(YYINITIAL); return ChasmTypes.TERNARY; }
<YYINITIAL> {LAMBDA} { yybegin(YYINITIAL); return ChasmTypes.LAMBDA; }
<YYINITIAL> {DOT} { yybegin(YYINITIAL); return ChasmTypes.DOT; }
<YYINITIAL> {COMMA} { yybegin(YYINITIAL); return ChasmTypes.COMMA; }
<YYINITIAL> {COLON} { yybegin(YYINITIAL); return ChasmTypes.COLON; }
<YYINITIAL> {LPAREN} { yybegin(YYINITIAL); return ChasmTypes.LPAREN; }
<YYINITIAL> {RPAREN} { yybegin(YYINITIAL); return ChasmTypes.RPAREN; }
<YYINITIAL> {LBRACKET} { yybegin(YYINITIAL); return ChasmTypes.LBRACKET; }
<YYINITIAL> {RBRACKET} { yybegin(YYINITIAL); return ChasmTypes.RBRACKET; }
<YYINITIAL> {LBRACE} { yybegin(YYINITIAL); return ChasmTypes.LBRACE; }
<YYINITIAL> {RBRACE} { yybegin(YYINITIAL); return ChasmTypes.RBRACE; }
<YYINITIAL> {DOLLAR} { yybegin(YYINITIAL); return ChasmTypes.DOLLAR; }

<YYINITIAL> {QUOTE} { yybegin(WAITING_CLOSE_QUOTE); return ChasmTypes.QUOTE; }
<WAITING_CLOSE_QUOTE> \\\" { yybegin(WAITING_CLOSE_QUOTE); return ChasmTypes.ESCAPED_STRING; }
<WAITING_CLOSE_QUOTE> \\\\ { yybegin(WAITING_CLOSE_QUOTE); return ChasmTypes.ESCAPED_STRING; }
<WAITING_CLOSE_QUOTE> \" { yybegin(YYINITIAL); return ChasmTypes.QUOTE; }
<WAITING_CLOSE_QUOTE> [^] { yybegin(WAITING_CLOSE_QUOTE); return ChasmTypes.UNESCAPED_STRING; }
<YYINITIAL> {SINGLE_QUOTE} { yybegin(WAITING_CLOSE_SINGLE_QUOTE); return ChasmTypes.SINGLE_QUOTE; }
<WAITING_CLOSE_SINGLE_QUOTE> \\' { yybegin(WAITING_CLOSE_SINGLE_QUOTE); return ChasmTypes.ESCAPED_STRING; }
<WAITING_CLOSE_SINGLE_QUOTE> \\\\ { yybegin(WAITING_CLOSE_SINGLE_QUOTE); return ChasmTypes.ESCAPED_STRING; }
<WAITING_CLOSE_SINGLE_QUOTE> ' { yybegin(YYINITIAL); return ChasmTypes.SINGLE_QUOTE; }
<WAITING_CLOSE_SINGLE_QUOTE> [^] { yybegin(WAITING_CLOSE_SINGLE_QUOTE); return ChasmTypes.UNESCAPED_STRING; }
<YYINITIAL> {BACKTICK} { yybegin(WAITING_CLOSE_BACKTICK); return ChasmTypes.BACKTICK; }
<WAITING_CLOSE_BACKTICK> \\` { yybegin(WAITING_CLOSE_BACKTICK); return ChasmTypes.ESCAPED_STRING; }
<WAITING_CLOSE_BACKTICK> \\\\ { yybegin(WAITING_CLOSE_BACKTICK); return ChasmTypes.ESCAPED_STRING; }
<WAITING_CLOSE_BACKTICK> ` { yybegin(YYINITIAL); return ChasmTypes.BACKTICK; }
<WAITING_CLOSE_BACKTICK> [^] { yybegin(WAITING_CLOSE_BACKTICK); return ChasmTypes.IDENT; }

<INSIDE_LINE_DOC_COMMENT> "\n" { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<INSIDE_LINE_DOC_COMMENT> [^] { yybegin(INSIDE_LINE_DOC_COMMENT); return ChasmTypes.DOC_COMMENT; }
<INSIDE_INLINE_DOC_COMMENT> "*/" { yybegin(YYINITIAL); return ChasmTypes.DOC_COMMENT_INLINE_END; }
<INSIDE_INLINE_DOC_COMMENT> [^] { yybegin(INSIDE_INLINE_DOC_COMMENT); return ChasmTypes.DOC_COMMENT; }
<INSIDE_LINE_COMMENT> "\n" { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<INSIDE_LINE_COMMENT> [^] { yybegin(INSIDE_LINE_COMMENT); return ChasmTypes.LINE_COMMENT; }
<INSIDE_INLINE_COMMENT> "*/" { yybegin(YYINITIAL); return ChasmTypes.COMMENT_INLINE_END; }
<INSIDE_INLINE_COMMENT> [^] { yybegin(INSIDE_INLINE_COMMENT); return ChasmTypes.INLINE_COMMENT; }

[^] { return TokenType.BAD_CHARACTER; }