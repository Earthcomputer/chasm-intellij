package net.earthcomputer.chasmintellij.formatter

import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizableOptions
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import net.earthcomputer.chasmintellij.ChasmLanguage

class ChasmLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
    override fun getLanguage() = ChasmLanguage

    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
        when (settingsType) {
            SettingsType.SPACING_SETTINGS -> {
                consumer.showStandardOptions(
                    "SPACE_AROUND_UNARY_OPERATOR",
                    "SPACE_AROUND_ADDITIVE_OPERATORS",
                    "SPACE_AROUND_MULTIPLICATIVE_OPERATORS",
                    "SPACE_AROUND_LOGICAL_OPERATORS",
                    "SPACE_AROUND_EQUALITY_OPERATORS",
                    "SPACE_AROUND_RELATIONAL_OPERATORS",
                    "SPACE_AROUND_BITWISE_OPERATORS",
                    "SPACE_AROUND_SHIFT_OPERATORS",
                    "SPACE_AROUND_LAMBDA_ARROW",
                    "SPACE_AFTER_COMMA",
                    "SPACE_BEFORE_COMMA",
                    "SPACE_AFTER_COLON",
                    "SPACE_BEFORE_COLON",
                    "SPACE_WITHIN_PARENTHESES",
                    "SPACE_BEFORE_METHOD_CALL_PARENTHESES",
                    "SPACE_WITHIN_METHOD_CALL_PARENTHESES",
                )
                consumer.renameStandardOption("SPACE_AROUND_EQUALITY_OPERATORS", "Equality operators (=, !=)")
                consumer.renameStandardOption("SPACE_AROUND_UNARY_OPERATOR", "Unary operators (!, -, +, ~)")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_BEFORE_INDEX_BRACKETS", "Index brackets", "Before parentheses")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_WITHIN_INDEX_BRACKETS", "Index brackets", "Within")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_BEFORE_TERNARY_QUESTION", "Before '?'", "In ternary operator (?:)")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_AFTER_TERNARY_QUESTION", "After '?'", "In ternary operator (?:)")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_BEFORE_DOT", "Before dot", "Other")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_AFTER_DOT", "After dot", "Other")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_BEFORE_MAP_COLON", "Before colon", "Other")
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "SPACE_AFTER_MAP_COLON", "After colon", "Other")
            }
            SettingsType.WRAPPING_AND_BRACES_SETTINGS -> {
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "MAP_WRAPPING", "Maps", "Brace placement",
                    CodeStyleSettingsCustomizableOptions.getInstance().WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES)
                consumer.showCustomOption(ChasmCodeStyleSettings::class.java, "LIST_WRAPPING", "Lists", "Brace placement",
                    CodeStyleSettingsCustomizableOptions.getInstance().WRAP_OPTIONS, CodeStyleSettingsCustomizable.WRAP_VALUES)
            }
            else -> {}
        }
    }

    override fun getCodeSample(settingsType: SettingsType) = """
        {
            complex_expression: -1 + 2 * 3 < 4 & 5 << 6 + (7 + 8),
            condition: false && true = false,
            ternary: condition ? "hello" : "world",
            func: arg -> "hello",
            list: [1, 2, 3],
            multiline_list: [
                1,
                2,
                3
            ],
            func_call: func(42),
            list_index: list[1],
            inner_map: {
                foo: "bar",
            }
            member_expr: inner_map.foo,
        }
    """.trimIndent()
}