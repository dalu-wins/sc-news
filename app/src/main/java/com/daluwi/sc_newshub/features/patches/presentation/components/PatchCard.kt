package com.daluwi.sc_newshub.features.patches.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.presentation.PatchEvent

@Composable
fun PatchCard(
    patch: Patch,
    onEvent: (PatchEvent) -> Unit,
    shape: RoundedCornerShape,
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = patch.channel.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${patch.version.mainVersion}.${patch.version.subVersion}.${patch.version.patch}-${
                        patch.channel.toString().lowercase()
                    }.${patch.build}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            FilledTonalIconButton(
                onClick = { onEvent(PatchEvent.VisitThread(uriHandler, patch.sourceUrl)) },
            ) {
                Icon(Icons.AutoMirrored.Filled.Notes, "Visit spectrum for official patch notes.")
            }
        }
    }
}
