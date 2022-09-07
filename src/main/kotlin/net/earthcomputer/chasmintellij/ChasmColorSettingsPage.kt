package net.earthcomputer.chasmintellij

import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class ChasmColorSettingsPage : ColorSettingsPage {
    companion object {
        private val ADDITIONAL_HIGHLIGHTING = mapOf(
            "key" to ChasmSyntaxHighlighter.KEY,
            "funcDecl" to ChasmSyntaxHighlighter.FUNCTION,
            "funcCall" to ChasmSyntaxHighlighter.FUNCTION_CALL,
            "param" to ChasmSyntaxHighlighter.PARAMETER,
        )

        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Identifier", ChasmSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Number", ChasmSyntaxHighlighter.NUMBER),
            AttributesDescriptor("String", ChasmSyntaxHighlighter.STRING),
            AttributesDescriptor("Keyword", ChasmSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Operator", ChasmSyntaxHighlighter.OPERATOR),
            AttributesDescriptor("Dot", ChasmSyntaxHighlighter.DOT),
            AttributesDescriptor("Comma", ChasmSyntaxHighlighter.COMMA),
            AttributesDescriptor("Colon", ChasmSyntaxHighlighter.COLON),
            AttributesDescriptor("Parentheses", ChasmSyntaxHighlighter.PARENTHESES),
            AttributesDescriptor("Brackets", ChasmSyntaxHighlighter.BRACKETS),
            AttributesDescriptor("Braces", ChasmSyntaxHighlighter.BRACES),
            AttributesDescriptor("Key", ChasmSyntaxHighlighter.KEY),
            AttributesDescriptor("Function declaration", ChasmSyntaxHighlighter.FUNCTION),
            AttributesDescriptor("Function call", ChasmSyntaxHighlighter.FUNCTION_CALL),
            AttributesDescriptor("Parameter", ChasmSyntaxHighlighter.PARAMETER),
            AttributesDescriptor("Bad value", ChasmSyntaxHighlighter.BAD_CHARACTER),
        )
    }

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = "Chassembly"

    override fun getIcon() = ChasmIcons.FILE

    override fun getHighlighter() = ChasmSyntaxHighlighter()

    override fun getDemoText() = """
        {
            <key>identifier</key>: hello,
            <key>number</key>: 123.45,
            <key>string</key>: "hello world",
            <key>keyword</key>: null,
            <key>operator</key>: 1 + 2,
            <key>dot</key>: foo.<key>bar</key>,
            <key>brackets</key>: [hello],
            <funcDecl>function</funcDecl>: <param>argument</param> -> "hello",
            <key>function_call</key>: <funcCall>function</funcCall>(0),
        }
    """.trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap() = ADDITIONAL_HIGHLIGHTING
}