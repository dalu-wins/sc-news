package com.daluwi.sc_news.features.patches.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.core.theme.Shapes
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.presentation.PatchEvent

fun LazyListScope.otherSection(
    patches: List<Patch>,
    onEvent: (PatchEvent) -> Unit
) {
    item {
        Text(
            text = "Other",
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    if (patches.isEmpty()) {

        item { NoPatchesCard(shape = Shapes.Card.Start) }

    } else {

        itemsIndexed(items = patches) { index, patch ->
            val shape = when (index) {
                0 -> Shapes.Card.Start

                else -> Shapes.Card.Middle
            }

            PatchCard(
                patch = patch,
                onEvent = { event: PatchEvent -> onEvent(event) },
                shape = shape,
            )
        }

    }

    item {
        val uriHandler = LocalUriHandler.current
        Button(
            onClick = { onEvent(PatchEvent.VisitSpectrum(uriHandler)) },
            shape = Shapes.Card.End,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "More on Spectrum")
        }
    }
}