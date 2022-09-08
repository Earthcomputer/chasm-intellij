package net.earthcomputer.chasmintellij.formatter

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.TokenSet
import net.earthcomputer.chasmintellij.ChasmLanguage
import net.earthcomputer.chasmintellij.psi.ChasmTypes

class ChasmFormattingModelBuilder : FormattingModelBuilder {
    companion object {
        private val UNARY_TOKENS = TokenSet.create(ChasmTypes.PLUS, ChasmTypes.MINUS, ChasmTypes.NOT, ChasmTypes.INVERT)
        private val ADDITIVE_TOKENS = TokenSet.create(ChasmTypes.PLUS, ChasmTypes.MINUS)
        private val MULTIPLICATIVE_TOKENS = TokenSet.create(ChasmTypes.MULTIPLY, ChasmTypes.DIVIDE, ChasmTypes.MODULO)
        private val LOGICAL_TOKENS = TokenSet.create(ChasmTypes.BOOL_AND, ChasmTypes.BOOL_OR)
        private val EQUALITY_TOKENS = TokenSet.create(ChasmTypes.EQUAL, ChasmTypes.NOT_EQUAL)
        private val RELATIONAL_TOKENS = TokenSet.create(ChasmTypes.LESS_THAN, ChasmTypes.LESS_THAN_EQUAL, ChasmTypes.GREATER_THAN, ChasmTypes.GREATER_THAN_EQUAL)
        private val BITWISE_TOKENS = TokenSet.create(ChasmTypes.BITWISE_AND, ChasmTypes.BITWISE_OR, ChasmTypes.BITWISE_XOR)
        private val SHIFT_TOKENS = TokenSet.create(ChasmTypes.SHIFT_LEFT, ChasmTypes.SHIFT_RIGHT, ChasmTypes.SHIFT_RIGHT_UNSIGNED)
    }

    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val settings = formattingContext.codeStyleSettings
        val spacingBuilder = createSpacingBuilder(settings)
        val block = ChasmBlock(formattingContext.node, null, null, Indent.getSmartIndent(Indent.Type.CONTINUATION), settings, spacingBuilder)
        return FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            block,
            settings
        )
    }

    private fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
        val commonSettings = settings.getCommonSettings(ChasmLanguage)
        val chasmSettings = settings.getCustomSettings(ChasmCodeStyleSettings::class.java)
        return SpacingBuilder(settings, ChasmLanguage)
            .afterInside(UNARY_TOKENS, ChasmTypes.UNARY_EXPRESSION).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)
            .aroundInside(ADDITIVE_TOKENS, ChasmTypes.ADDITIVE_EXPRESSION).spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)
            .around(MULTIPLICATIVE_TOKENS).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
            .around(LOGICAL_TOKENS).spaceIf(commonSettings.SPACE_AROUND_LOGICAL_OPERATORS)
            .around(EQUALITY_TOKENS).spaceIf(commonSettings.SPACE_AROUND_EQUALITY_OPERATORS)
            .around(RELATIONAL_TOKENS).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .around(BITWISE_TOKENS).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)
            .around(SHIFT_TOKENS).spaceIf(commonSettings.SPACE_AROUND_SHIFT_OPERATORS)
            .around(ChasmTypes.LAMBDA).spaceIf(commonSettings.SPACE_AROUND_LAMBDA_ARROW)
            .after(ChasmTypes.COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)
            .before(ChasmTypes.COMMA).spaceIf(commonSettings.SPACE_BEFORE_COMMA)
            .afterInside(ChasmTypes.COLON, ChasmTypes.MAP_ENTRY).spaceIf(chasmSettings.SPACE_AFTER_MAP_COLON)
            .beforeInside(ChasmTypes.COLON, ChasmTypes.MAP_ENTRY).spaceIf(chasmSettings.SPACE_BEFORE_MAP_COLON)
            .afterInside(ChasmTypes.LPAREN, ChasmTypes.PARENTHESES_EXPRESSION).spaceIf(commonSettings.SPACE_WITHIN_PARENTHESES)
            .beforeInside(ChasmTypes.RPAREN, ChasmTypes.PARENTHESES_EXPRESSION).spaceIf(commonSettings.SPACE_WITHIN_PARENTHESES)
            .beforeInside(ChasmTypes.LPAREN, ChasmTypes.CALL_EXPRESSION).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)
            .afterInside(ChasmTypes.LPAREN, ChasmTypes.CALL_EXPRESSION).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)
            .beforeInside(ChasmTypes.RPAREN, ChasmTypes.CALL_EXPRESSION).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)
            .beforeInside(ChasmTypes.LBRACKET, ChasmTypes.INDEX_EXPRESSION).spaceIf(chasmSettings.SPACE_BEFORE_INDEX_BRACKETS)
            .afterInside(ChasmTypes.LBRACKET, ChasmTypes.INDEX_EXPRESSION).spaceIf(chasmSettings.SPACE_WITHIN_INDEX_BRACKETS)
            .beforeInside(ChasmTypes.RBRACKET, ChasmTypes.INDEX_EXPRESSION).spaceIf(chasmSettings.SPACE_WITHIN_INDEX_BRACKETS)
            .before(ChasmTypes.TERNARY).spaceIf(chasmSettings.SPACE_BEFORE_TERNARY_QUESTION)
            .after(ChasmTypes.TERNARY).spaceIf(chasmSettings.SPACE_AFTER_TERNARY_QUESTION)
            .beforeInside(ChasmTypes.COLON, ChasmTypes.TERNARY_EXPRESSION).spaceIf(commonSettings.SPACE_BEFORE_COLON)
            .afterInside(ChasmTypes.COLON, ChasmTypes.TERNARY_EXPRESSION).spaceIf(commonSettings.SPACE_AFTER_COLON)
            .before(ChasmTypes.DOT).spaceIf(chasmSettings.SPACE_BEFORE_DOT)
            .after(ChasmTypes.DOT).spaceIf(chasmSettings.SPACE_AFTER_DOT)
    }
}