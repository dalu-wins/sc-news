package com.daluwi.sc_newshub.features.settings.presentation

import com.daluwi.sc_newshub.features.settings.domain.models.Category
import com.daluwi.sc_newshub.features.settings.domain.models.Setting

data class SettingsState(
    val settings: List<Setting> = listOf(
        Setting.SwitchSetting(
            name = "Dynamic Colors",
            isSet = true,
            category = Category.Display
        ),
        Setting.SwitchSetting(
            name = "System Brightness",
            isSet = true,
            category = Category.Display

        ),
        Setting.SwitchSetting(
            name = "Dark Mode",
            isSet = true,
            category = Category.Display
        ),
    )
)