package com.daluwi.sc_newshub.features.settings.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daluwi.sc_newshub.core.theme.CORNER_RADIUS_BIG
import com.daluwi.sc_newshub.core.theme.CORNER_RADIUS_SMALL
import com.daluwi.sc_newshub.core.theme.VERTICAL_PADDING
import com.daluwi.sc_newshub.features.settings.domain.models.Setting
import com.daluwi.sc_newshub.features.settings.presentation.SettingsEvent

fun LazyListScope.displaySection(
    settings: List<Setting>,
    onEvent: (SettingsEvent) -> Unit
) {
    item {
        Text(
            text = "Display",
            modifier = Modifier.padding(vertical = VERTICAL_PADDING.dp),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    itemsIndexed(items = settings) { index, setting ->

        var shape = when (index) {
            0 -> RoundedCornerShape(
                topStart = CORNER_RADIUS_BIG.dp,
                topEnd = CORNER_RADIUS_BIG.dp,
                bottomStart = CORNER_RADIUS_SMALL.dp,
                bottomEnd = CORNER_RADIUS_SMALL.dp
            )

            settings.lastIndex -> RoundedCornerShape(
                topStart = CORNER_RADIUS_SMALL.dp,
                topEnd = CORNER_RADIUS_SMALL.dp,
                bottomStart = CORNER_RADIUS_BIG.dp,
                bottomEnd = CORNER_RADIUS_BIG.dp
            )

            else -> RoundedCornerShape(size = CORNER_RADIUS_SMALL.dp)
        }

        if (settings.size == 1) shape = RoundedCornerShape(
            topStart = CORNER_RADIUS_BIG.dp,
            topEnd = CORNER_RADIUS_BIG.dp,
            bottomStart = CORNER_RADIUS_BIG.dp,
            bottomEnd = CORNER_RADIUS_BIG.dp
        )

        when (setting) {
            is Setting.SwitchSetting -> {
                SwitchCard(
                    setting = setting,
                    onEvent = onEvent,
                    shape = shape,
                )
            }
        }

    }

}