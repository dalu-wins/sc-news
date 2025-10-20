package com.daluwi.sc_newshub.features.patches.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.daluwi.sc_newshub.core.theme.CORNER_RADIUS_BIG
import com.daluwi.sc_newshub.core.theme.CORNER_RADIUS_SMALL
import com.daluwi.sc_newshub.core.theme.VERTICAL_PADDING
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.presentation.PatchEvent

fun LazyListScope.otherSection(
    patches: List<Patch>,
    onEvent: (PatchEvent) -> Unit
) {
    item {
        Text(
            text = "Other",
            modifier = Modifier.padding(vertical = VERTICAL_PADDING.dp),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    itemsIndexed(items = patches) { index, patch ->
        val shape = when (index) {
            0 -> RoundedCornerShape(
                topStart = CORNER_RADIUS_BIG.dp,
                topEnd = CORNER_RADIUS_BIG.dp,
                bottomStart = CORNER_RADIUS_SMALL.dp,
                bottomEnd = CORNER_RADIUS_SMALL.dp
            )

            else -> RoundedCornerShape(size = CORNER_RADIUS_SMALL.dp)
        }

        PatchCard(
            patch = patch,
            onEvent = { event: PatchEvent -> onEvent(event) },
            shape = shape,
        )
    }

    item {
        val uriHandler = LocalUriHandler.current
        Button(
            onClick = { onEvent(PatchEvent.VisitSpectrum(uriHandler)) },
            shape = RoundedCornerShape(
                topStart = CORNER_RADIUS_SMALL.dp,
                topEnd = CORNER_RADIUS_SMALL.dp,
                bottomStart = CORNER_RADIUS_BIG.dp,
                bottomEnd = CORNER_RADIUS_BIG.dp
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "More on Spectrum")
        }
    }
}