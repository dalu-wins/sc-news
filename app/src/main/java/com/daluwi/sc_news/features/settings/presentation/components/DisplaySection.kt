package com.daluwi.sc_news.features.settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.core.theme.Shapes
import com.daluwi.sc_news.features.settings.presentation.SettingsEvent
import com.daluwi.sc_news.features.settings.presentation.SettingsState

fun LazyListScope.displaySection(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    item {
        Text(
            text = "Display",
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    item {
        SwitchCard(
            name = "Use Dynamic Colors",
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