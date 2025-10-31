package com.daluwi.sc_news.features.patches.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.Shapes
import com.daluwi.sc_news.core.theme.UiText

@Composable
fun NoPatchesCard(shape: Shape) {
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
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = UiText.StringResource(R.string.empty_patch_list).asString(),
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline),
            )
        }
    }
}