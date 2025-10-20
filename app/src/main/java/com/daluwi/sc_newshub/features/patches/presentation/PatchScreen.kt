package com.daluwi.sc_newshub.features.patches.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_newshub.features.patches.presentation.components.PatchCard
import com.daluwi.sc_newshub.features.patches.presentation.components.PatchFAB

private const val SMALL_RADIUS: Int = 8
private const val BIG_RADIUS: Int = 24
private const val SPACING: Int = 6
private const val HORIZONTAL_PADDING: Int = 12
private const val VERTICAL_PADDING: Int = 12
private const val FAB_SPACING: Int = 64

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatchScreen(
    viewModel: PatchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val uriHandler = LocalUriHandler.current

    val pinnedPatches = state.patches.filter { it.pinned }
    val otherPatches = state.patches.filter { !it.pinned }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = { PatchFAB() }
    ) { contentPadding ->
        if (state.patches.isEmpty()) {
            Text(
                "Keine Builds gefunden.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(HORIZONTAL_PADDING.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(SPACING.dp),
                contentPadding = PaddingValues(
                    top = VERTICAL_PADDING.dp,
                    bottom = VERTICAL_PADDING.dp,
                    start = HORIZONTAL_PADDING.dp,
                    end = HORIZONTAL_PADDING.dp,
                )
            ) {
                item {
                    Text(
                        "Pinned",
                        modifier = Modifier.padding(vertical = VERTICAL_PADDING.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                itemsIndexed(pinnedPatches) { index, build ->
                    val shape = when (index) {
                        0 -> RoundedCornerShape(
                            topStart = BIG_RADIUS.dp,
                            topEnd = BIG_RADIUS.dp,
                            bottomStart = SMALL_RADIUS.dp,
                            bottomEnd = SMALL_RADIUS.dp
                        )

                        pinnedPatches.lastIndex -> RoundedCornerShape(
                            topStart = SMALL_RADIUS.dp,
                            topEnd = SMALL_RADIUS.dp,
                            bottomStart = BIG_RADIUS.dp,
                            bottomEnd = BIG_RADIUS.dp
                        )

                        else -> RoundedCornerShape(SMALL_RADIUS.dp)
                    }

                    PatchCard(
                        patch = build,
                        onEvent = { event: PatchEvent -> viewModel.onEvent(event) },
                        shape = shape,
                    )
                }
                item {
                    Text(
                        "Other",
                        modifier = Modifier.padding(vertical = VERTICAL_PADDING.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                itemsIndexed(otherPatches) { index, patch ->
                    val shape = when (index) {
                        0 -> RoundedCornerShape(
                            topStart = BIG_RADIUS.dp,
                            topEnd = BIG_RADIUS.dp,
                            bottomStart = SMALL_RADIUS.dp,
                            bottomEnd = SMALL_RADIUS.dp
                        )

                        else -> RoundedCornerShape(SMALL_RADIUS.dp)
                    }

                    PatchCard(
                        patch = patch,
                        onEvent = { event: PatchEvent -> viewModel.onEvent(event) },
                        shape = shape,
                    )
                }
                item {
                    Button(
                        onClick = { viewModel.onEvent(PatchEvent.VisitSpectrum(uriHandler)) },
                        shape = RoundedCornerShape(
                            topStart = SMALL_RADIUS.dp,
                            topEnd = SMALL_RADIUS.dp,
                            bottomStart = BIG_RADIUS.dp,
                            bottomEnd = BIG_RADIUS.dp
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("More on Spectrum")
                    }
                }
                item {
                    Spacer(Modifier.height(FAB_SPACING.dp))
                }
            }

        }
    }
}
