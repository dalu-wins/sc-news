package com.daluwi.sc_news.features.patches.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.Shapes
import com.daluwi.sc_news.core.theme.UiText.StringResource
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.models.Wave
import com.daluwi.sc_news.features.patches.presentation.PatchEvent

// TODO fix variables...
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
        onClick = { onEvent(PatchEvent.VisitThread(uriHandler, patch.sourceUrl)) },
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

            val channelName = when (patch.channel) {
                is Channel.Live -> StringResource(R.string.channel_live).asString()
                is Channel.EPTU -> StringResource(R.string.channel_eptu).asString()
                is Channel.PTU -> StringResource(R.string.channel_ptu).asString()
                is Channel.Hotfix -> StringResource(R.string.channel_hotfix).asString()
                is Channel.Preview -> StringResource(R.string.channel_preview).asString()
                Channel.Unknown -> "Unknown"
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

                Text(
                    "${patch.version.major}.${patch.version.minor}.${patch.version.patch}",
                    style = MaterialTheme.typography.titleLarge
                )

                var buildText = patch.build
                if (buildText.isEmpty()) buildText = "not found"
                Text(
                    "BUILD $buildText",
                    style = MaterialTheme.typography.labelSmall
                )

            }

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {

                if (patch.channel is Channel.PTU) {

                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = WAVE_BADGE_BACKGROUND_ALPHA),
                                shape = Shapes.Badge.Left
                            )
                            .padding(
                                horizontal = WAVE_BADGE_HORIZONTAL_PADDING.dp,
                                vertical = WAVE_BADGE_VERTICAL_PADDING.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = channelName,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = WAVE_BADGE_FONT_SIZE.sp
                        )
                    }

                    val waveText = when (patch.channel.wave) {
                        Wave.One -> StringResource(R.string.ptu_wave_1).asString()
                        Wave.Two -> StringResource(R.string.ptu_wave_2).asString()
                        Wave.Three -> StringResource(R.string.ptu_wave_3).asString()
                        Wave.Four -> StringResource(R.string.ptu_wave_4).asString()
                        Wave.AllBackers -> StringResource(R.string.ptu_all_backers)
                            .asString()

                        Wave.Unknown -> "Unknown"
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = WAVE_BADGE_BACKGROUND_ALPHA),
                                shape = Shapes.Badge.Right
                            )
                            .padding(
                                horizontal = WAVE_BADGE_HORIZONTAL_PADDING.dp,
                                vertical = WAVE_BADGE_VERTICAL_PADDING.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = waveText,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = WAVE_BADGE_FONT_SIZE.sp
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = WAVE_BADGE_BACKGROUND_ALPHA),
                                shape = Shapes.Badge.Single
                            )
                            .padding(
                                horizontal = WAVE_BADGE_HORIZONTAL_PADDING.dp,
                                vertical = WAVE_BADGE_VERTICAL_PADDING.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = channelName,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = WAVE_BADGE_FONT_SIZE.sp
                        )
                    }
                }
            }

        }
    }
}
