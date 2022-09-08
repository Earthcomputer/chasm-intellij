package net.earthcomputer.chasmintellij.formatter

import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.codeStyle.CustomCodeStyleSettings
import net.earthcomputer.chasmintellij.ChasmLanguage

@Suppress("PropertyName")
class ChasmCodeStyleSettings(container: CodeStyleSettings) : CustomCodeStyleSettings(ChasmLanguage.id, container) {
    @JvmField
    var MAP_WRAPPING = CommonCodeStyleSettings.WRAP_ALWAYS
    @JvmField
    var LIST_WRAPPING = CommonCodeStyleSettings.WRAP_AS_NEEDED or CommonCodeStyleSettings.WRAP_ON_EVERY_ITEM

    @JvmField
    var SPACE_BEFORE_INDEX_BRACKETS = false
    @JvmField
    var SPACE_WITHIN_INDEX_BRACKETS = false

    @JvmField
    var SPACE_BEFORE_TERNARY_QUESTION = true
    @JvmField
    var SPACE_AFTER_TERNARY_QUESTION = true

    @JvmField
    var SPACE_BEFORE_MAP_COLON = false
    @JvmField
    var SPACE_AFTER_MAP_COLON = true

    @JvmField
    var SPACE_BEFORE_DOT = false
    @JvmField
    var SPACE_AFTER_DOT = false
}