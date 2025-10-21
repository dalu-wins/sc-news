package com.daluwi.sc_newshub.features.settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daluwi.sc_newshub.core.theme.HORIZONTAL_PADDING
import com.daluwi.sc_newshub.core.theme.VERTICAL_PADDING
import com.daluwi.sc_newshub.core.theme.cardShapeSingle
import com.daluwi.sc_newshub.features.settings.presentation.SettingsEvent
import com.daluwi.sc_newshub.features.settings.presentation.SettingsState

fun LazyListScope.displaySection(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    item {
        Text(
            text = "Display",
            modifier = Modifier.padding(
                vertical = VERTICAL_PADDING.dp,
                horizontal = HORIZONTAL_PADDING.dp
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
            shape = cardShapeSingle,
        )
    }

}