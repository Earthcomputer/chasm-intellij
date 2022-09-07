package net.earthcomputer.chasmintellij

import com.intellij.openapi.fileTypes.LanguageFileType

object ChasmFileType : LanguageFileType(ChasmLanguage) {
    override fun getName() = "Chassembly"

    override fun getDescription() = "Chassembly file"

    override fun getDefaultExtension() = "chasm"

    override fun getIcon() = ChasmIcons.FILE
}