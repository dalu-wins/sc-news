package com.daluwi.sc_news.features.settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.core.theme.Shapes
import com.daluwi.sc_news.core.theme.UiText
import com.daluwi.sc_news.features.settings.presentation.SettingsEvent
import com.daluwi.sc_news.features.settings.presentation.SettingsState

fun LazyListScope.settingsSection(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    item {
        Text(
            text = UiText.StringResource(R.string.settings_section).asString(),
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    item {
        SwitchCard(
            name = UiText.StringResource(R.string.setting_dynamic_colors).asString(),
            checked = state.dynamicColors,
            setSwitch = { useDynamicColors ->
                onEvent(
                    SettingsEvent.UseDynamicColors(
                        useDynamicColors
                    )
                )
            },
            shape = Shapes.Card.Single,
        )
    }

}