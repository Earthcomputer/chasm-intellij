package net.earthcomputer.chasmintellij.formatter

import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import net.earthcomputer.chasmintellij.ChasmLanguage

class ChasmCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
    override fun createCustomSettings(settings: CodeStyleSettings) = ChasmCodeStyleSettings(settings)

    override fun createConfigurable(
        settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ) = object : CodeStyleAbstractConfigurable(settings, modelSettings, configurableDisplayName) {
        override fun createPanel(settings: CodeStyleSettings) = ChasmCodeStyleMainPanel(currentSettings, settings)
    }

    override fun getConfigurableDisplayName() = ChasmLanguage.displayName

    private class ChasmCodeStyleMainPanel(
        currentSettings: CodeStyleSettings,
        settings: CodeStyleSettings
    ) : TabbedLanguageCodeStylePanel(ChasmLanguage, currentSettings, settings)
}