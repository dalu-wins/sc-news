package com.daluwi.sc_newshub.features.settings.domain.models

sealed class Setting(open val name: String, open val category: Category) {
    data class SwitchSetting(
        override val name: String,
        override val category: Category,
        val isSet: Boolean,
    ) : Setting(name, category)
}
