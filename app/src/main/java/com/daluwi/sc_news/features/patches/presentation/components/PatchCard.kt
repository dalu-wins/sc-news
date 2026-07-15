package com.daluwi.sc_news.features.patches.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.UiText.StringResource
import com.daluwi.sc_news.features.patches.domain.models.Channel
import com.daluwi.sc_news.features.patches.domain.models.Patch
import com.daluwi.sc_news.features.patches.domain.models.Wave
import com.daluwi.sc_news.features.patches.presentation.PatchEvent

// TODO fix variables...
private const val WAVE_BADGE_HORIZONTAL_PADDING: Int = 12
private const val WAVE_BADGE_VERTICAL_PADDING: Int = 8
private const val WAVE_BADGE_FONT_SIZE: Int = 14
private const val WAVE_BADGE_BACKGROUND_ALPHA: Float = 0.1f

private const val VERSION_PADDING: Int = 0

@Composable
fun PatchCard(
    patch: Patch,
    onEvent: (PatchEvent) -> Unit,
    shape: RoundedCornerShape,
    showBuild: Boolean
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(shape),
        shape = shape,
        onClick = { onEvent(PatchEvent.VisitThread(uriHandler, patch.sourceUrl)) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = VERSION_PADDING.dp,
                    end = VERSION_PADDING.dp,
                    top = VERSION_PADDING.dp,
                    bottom = VERSION_PADDING.dp
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

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(80.dp)
                        .fillMaxHeight()
                ) {


                    if (showBuild) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .background(
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = WAVE_BADGE_BACKGROUND_ALPHA),
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                "${patch.version.major}.${patch.version.minor}.${patch.version.patch}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
                                )
                                .padding(
                                    horizontal = WAVE_BADGE_HORIZONTAL_PADDING.dp,
                                    vertical = 0.dp
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            var buildText = patch.build
                            if (buildText.isEmpty()) buildText = "not found"
                            Text(
                                buildText,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = WAVE_BADGE_BACKGROUND_ALPHA),
                                )
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 10.dp
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                "${patch.version.major}.${patch.version.minor}.${patch.version.patch}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                        }

                    }


                }

                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = channelName,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = WAVE_BADGE_FONT_SIZE.sp,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )

                    val secondaryText: String = StringResource(R.string.open_to).asString() +
                            ": " + when (patch.channel) {
                        is Channel.PTU -> {
                            when (patch.channel.wave) {
                                Wave.One -> StringResource(R.string.ptu_wave_1).asString()
                                Wave.Two -> StringResource(R.string.ptu_wave_2).asString()
                                Wave.Three -> StringResource(R.string.ptu_wave_3).asString()
                                Wave.Four -> StringResource(R.string.ptu_wave_4).asString()
                                Wave.AllBackers -> StringResource(R.string.ptu_all_backers)
                                    .asString()

                                Wave.Unknown -> StringResource(R.string.unknown).asString()
                            }

                        }

                        is Channel.EPTU -> StringResource(R.string.evocati).asString()

                        Channel.Hotfix -> StringResource(R.string.unknown).asString()
                        Channel.Live -> StringResource(R.string.ptu_all_backers).asString()
                        Channel.Preview -> StringResource(R.string.unknown).asString()
                        Channel.Unknown -> StringResource(R.string.unknown).asString()
                    }
                    Text(
                        text = secondaryText,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 12.sp
                    )

                }


            }

        }

    }


}
