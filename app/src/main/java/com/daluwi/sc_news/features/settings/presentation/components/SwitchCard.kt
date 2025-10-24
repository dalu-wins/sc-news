package com.daluwi.sc_news.features.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.core.theme.Shapes

@Composable
fun SwitchCard(
    name: String,
    checked: Boolean,
    setSwitch: (Boolean) -> Unit,
    shape: Shape,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Shapes.Card.HORIZONTAL_PADDING.dp,
                    vertical = Shapes.Card.VERTICAL_PADDING.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(name)
            Switch(
                checked = checked,
                onCheckedChange = { isSet -> setSwitch(isSet) })
        }
    }
}