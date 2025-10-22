package com.daluwi.sc_newshub.features.patches.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daluwi.sc_newshub.core.theme.Shapes
import com.daluwi.sc_newshub.features.patches.domain.models.Channel
import com.daluwi.sc_newshub.features.patches.domain.models.Patch
import com.daluwi.sc_newshub.features.patches.domain.models.Wave
import com.daluwi.sc_newshub.features.patches.presentation.PatchEvent

private const val WAVE_BADGE_SPACING: Int = 12
private const val WAVE_BADGE_CORNER_RADIUS: Int = 16
private const val WAVE_BADGE_HORIZONTAL_PADDING: Int = 12
private const val WAVE_BADGE_VERTICAL_PADDING: Int = 4
private const val WAVE_BADGE_FONT_SIZE: Int = 14
private const val WAVE_BADGE_BACKGROUND_ALPHA: Float = 0.1f

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
                .padding(
                    horizontal = Shapes.Card.HORIZONTAL_PADDING.dp,
                    vertical = Shapes.Card.VERTICAL_PADDING.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(WAVE_BADGE_SPACING.dp)
            ) {
                if (patch.channel is Channel.PTU) {
                    val waveText = when (patch.channel.wave) {
                        Wave.One -> "Wave 1"
                        Wave.Two -> "Wave 2"
                        Wave.Three -> "Wave 3"
                        Wave.Four -> "Wave 4"
                        Wave.AllBackers -> "All Backers"
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = WAVE_BADGE_BACKGROUND_ALPHA),
                                shape = RoundedCornerShape(WAVE_BADGE_CORNER_RADIUS.dp)
                            )
                            .padding(
                                horizontal = WAVE_BADGE_HORIZONTAL_PADDING.dp,
                                vertical = WAVE_BADGE_VERTICAL_PADDING.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = waveText,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = WAVE_BADGE_FONT_SIZE.sp
                        )
                    }
                }
                FilledTonalIconButton(
                    onClick = { onEvent(PatchEvent.VisitThread(uriHandler, patch.sourceUrl)) },
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Notes,
                        "Visit spectrum for official patch notes."
                    )
                }
            }

        }
    }
}
