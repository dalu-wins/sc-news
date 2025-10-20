package com.daluwi.sc_newshub.features.patches.presentation.components

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
import com.daluwi.sc_newshub.core.theme.HORIZONTAL_PADDING
import com.daluwi.sc_newshub.core.theme.VERTICAL_PADDING
import com.daluwi.sc_newshub.core.theme.cardShapeMiddle
import com.daluwi.sc_newshub.core.theme.cardShapeSingle
import com.daluwi.sc_newshub.core.theme.cardShapeStart
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.presentation.PatchEvent

fun LazyListScope.pinnedSection(
    patches: List<Patch>,
    onEvent: (PatchEvent) -> Unit
) {
    item {
        Text(
            text = "Pinned",
            modifier = Modifier.padding(
                vertical = VERTICAL_PADDING.dp,
                horizontal = HORIZONTAL_PADDING.dp
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    itemsIndexed(items = patches) { index, patch ->
        var shape = when (index) {
            0 -> cardShapeStart

            patches.lastIndex -> RoundedCornerShape(
                topStart = CORNER_RADIUS_SMALL.dp,
                topEnd = CORNER_RADIUS_SMALL.dp,
                bottomStart = CORNER_RADIUS_BIG.dp,
                bottomEnd = CORNER_RADIUS_BIG.dp
            )

            else -> cardShapeMiddle
        }

        if (patches.size == 1) shape = cardShapeSingle

        PatchCard(
            patch = patch,
            onEvent = onEvent,
            shape = shape,
        )
    }
}
