package net.earthcomputer.chasmintellij

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import net.earthcomputer.chasmintellij.psi.ChasmTypes

class ChasmSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val IDENTIFIER = createTextAttributesKey("CHASM_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val NUMBER = createTextAttributesKey("CHASM_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val STRING = createTextAttributesKey("CHASM_STRING", DefaultLanguageHighlighterColors.STRING)
        val ESCAPED_STRING = createTextAttributesKey("CHASM_ESCAPED_STRING", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
        val KEYWORD = createTextAttributesKey("CHASM_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val OPERATOR = createTextAttributesKey("CHASM_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val DOT = createTextAttributesKey("CHASM_DOT", DefaultLanguageHighlighterColors.DOT)
        val COMMA = createTextAttributesKey("CHASM_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val COLON = createTextAttributesKey("CHASM_COLON", OPERATOR)
        val PARENTHESES = createTextAttributesKey("CHASM_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val BRACKETS = createTextAttributesKey("CHASM_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val BRACES = createTextAttributesKey("CHASM_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val KEY = createTextAttributesKey("CHASM_KEY", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
        val FUNCTION = createTextAttributesKey("CHASM_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
        val FUNCTION_CALL = createTextAttributesKey("CHASM_FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val PARAMETER = createTextAttributesKey("CHASM_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
        val BAD_CHARACTER = createTextAttributesKey("CHASM_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        val NUMBER_KEYS = arrayOf(NUMBER)
        val STRING_KEYS = arrayOf(STRING)
        val ESCAPED_STRING_KEYS = arrayOf(ESCAPED_STRING)
        val KEYWORD_KEYS = arrayOf(KEYWORD)
        val OPERATOR_KEYS = arrayOf(OPERATOR)
        val DOT_KEYS = arrayOf(DOT)
        val COMMA_KEYS = arrayOf(COMMA)
        val COLON_KEYS = arrayOf(COLON)
        val PARENTHESES_KEYS = arrayOf(PARENTHESES)
        val BRACKETS_KEYS = arrayOf(BRACKETS)
        val BRACES_KEYS = arrayOf(BRACES)
        val BAD_CHARACTER_KEYS = arrayOf(BAD_CHARACTER)
    }

    override fun getHighlightingLexer() = ChasmLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        return when (tokenType) {
            ChasmTypes.IDENT,
            ChasmTypes.DOLLAR,
            ChasmTypes.BACKTICK -> IDENTIFIER_KEYS
            ChasmTypes.INTEGER,
            ChasmTypes.FLOAT -> NUMBER_KEYS
            ChasmTypes.UNESCAPED_STRING,
            ChasmTypes.QUOTE,
            ChasmTypes.SINGLE_QUOTE -> STRING_KEYS
            ChasmTypes.ESCAPED_STRING -> ESCAPED_STRING_KEYS
            ChasmTypes.NULL,
            ChasmTypes.BOOL -> KEYWORD_KEYS
            ChasmTypes.BITWISE_AND,
            ChasmTypes.BITWISE_OR,
            ChasmTypes.BITWISE_XOR,
            ChasmTypes.SHIFT_LEFT,
            ChasmTypes.SHIFT_RIGHT,
            ChasmTypes.SHIFT_RIGHT_UNSIGNED,
            ChasmTypes.BOOL_AND,
            ChasmTypes.BOOL_OR,
            ChasmTypes.EQUAL,
            ChasmTypes.NOT_EQUAL,
            ChasmTypes.LESS_THAN,
            ChasmTypes.LESS_THAN_EQUAL,
            ChasmTypes.GREATER_THAN,
            ChasmTypes.GREATER_THAN_EQUAL,
            ChasmTypes.PLUS,
            ChasmTypes.MINUS,
            ChasmTypes.MULTIPLY,
            ChasmTypes.DIVIDE,
            ChasmTypes.MODULO,
            ChasmTypes.NOT,
            ChasmTypes.INVERT,
            ChasmTypes.TERNARY,
            ChasmTypes.LAMBDA -> OPERATOR_KEYS
            ChasmTypes.DOT -> DOT_KEYS
            ChasmTypes.COMMA -> COMMA_KEYS
            ChasmTypes.COLON -> COLON_KEYS
            ChasmTypes.LPAREN,
            ChasmTypes.RPAREN -> PARENTHESES_KEYS
            ChasmTypes.LBRACKET,
            ChasmTypes.RBRACKET -> BRACKETS_KEYS
            ChasmTypes.LBRACE,
            ChasmTypes.RBRACE -> BRACES_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHARACTER_KEYS
            else -> TextAttributesKey.EMPTY_ARRAY
        }
    }
}