package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.features.patches.presentation.components.PatchFAB
import com.daluwi.sc_news.features.patches.presentation.components.otherSection
import com.daluwi.sc_news.features.patches.presentation.components.pinnedSection

private const val ITEM_SPACING: Int = 6

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatchScreen(
    viewModel: PatchViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    val pinnedPatches = state.patches.filter { it.pinned }
    val otherPatches = state.patches.filter { !it.pinned }

    // Error Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.errors.collect { eventType ->
            snackbarHostState.showSnackbar(eventType.message.asString(context))
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            PatchFAB { event -> viewModel.onEvent(event) }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { contentPadding ->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(ITEM_SPACING.dp),
            contentPadding = PaddingValues(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp,
            )
        ) {

            item {
                AnimatedVisibility(state.isLoading, modifier = Modifier.fillMaxWidth()) {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }
            }

            pinnedSection(
                patches = pinnedPatches,
                onEvent = { event -> viewModel.onEvent(event) },
            )

            item {
                Spacer(Modifier.height(ITEM_SPACING.dp))
            }

            otherSection(
                patches = otherPatches,
                onEvent = { event -> viewModel.onEvent(event) }
            )

            item {
                Spacer(
                    modifier = Modifier
                        .height(
                            height = Dimensions.FAB_SPACER_HEIGHT.dp +
                                    contentPadding.calculateBottomPadding()
                        )
                )
            }
        }

    }
}
