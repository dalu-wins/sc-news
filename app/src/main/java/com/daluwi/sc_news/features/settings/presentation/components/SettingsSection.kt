package com.daluwi.sc_news.features.settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.core.theme.BLUE
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.core.theme.GOLD
import com.daluwi.sc_news.core.theme.Shapes
import com.daluwi.sc_news.features.settings.presentation.SettingsEvent
import com.daluwi.sc_news.features.settings.presentation.SettingsState

fun LazyListScope.settingsSection(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    item {
        Text(
            text = "Theme",
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    item {
        SwitchCard(
            name = "Dynamic Colors",
            checked = state.dynamicColors,
            setSwitch = { useDynamicColors ->
                onEvent(
                    SettingsEvent.UseDynamicColors(
                        useDynamicColors
                    )
                )
            },
            shape = Shapes.Card.Start,
        )
    }

    item {
        val colors = listOf(BLUE, GOLD)
        ColorSelectorCard(
            name = "Theme Color",
            selectedColor = state.color,
            availableColors = colors,
            onColorSelected = { color -> onEvent(SettingsEvent.SetCustomColor(color)) },
            shape = Shapes.Card.End
        )
    }

    item {

        Text(
            text = "Startup Behavior",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
        )

    }

    item {
        SwitchCard(
            name = "Show Build of Current Patches", checked = state.build,
            setSwitch = { showBuild ->
                onEvent(
                    SettingsEvent.ShowBuild(
                        showBuild
                    )
                )
            },
            shape = Shapes.Card.Single,
        )
    }

    item {
        Text(
            text = "Changes in this section will take effect on restart of the app.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
        )
    }

}