package com.daluwi.sc_news.features.patches.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.core.theme.Dimensions.LIST_ITEM_SPACING
import com.daluwi.sc_news.features.patches.presentation.components.otherSection
import com.daluwi.sc_news.features.patches.presentation.components.pinnedSection

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatchScreen(
    modifier: Modifier = Modifier,
    viewModel: PatchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Error Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.errors.collect { eventType ->
            snackbarHostState.showSnackbar(eventType.message.asString(context))
        }
    }

    // Refresh
    val pullToRefreshState = rememberPullToRefreshState()

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->

        PullToRefreshBox(
            state = pullToRefreshState,
            isRefreshing = state.isLoading,
            contentAlignment = Alignment.TopCenter,
            onRefresh = { viewModel.onEvent(PatchEvent.Refresh) },
            indicator = {
                PullToRefreshDefaults.LoadingIndicator(
                    state = pullToRefreshState,
                    isRefreshing = state.isLoading,
                )
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(LIST_ITEM_SPACING.dp),
                contentPadding = PaddingValues(
                    vertical = Dimensions.VERTICAL_PADDING.dp,
                    horizontal = Dimensions.HORIZONTAL_PADDING.dp,
                )
            ) {

                pinnedSection(
                    patches = state.pinnedPatches,
                    onEvent = { event -> viewModel.onEvent(event) },
                )


                item {
                    Spacer(Modifier.height(LIST_ITEM_SPACING.dp))
                }

                otherSection(
                    patches = state.otherPatches,
                    onEvent = { event -> viewModel.onEvent(event) }
                )

                item {
                    Spacer(Modifier.height(LIST_ITEM_SPACING.dp))
                }

                item {
                    Spacer(Modifier.height(46.dp))
                }

                item {
                    Spacer(Modifier.height(LIST_ITEM_SPACING.dp))
                }

            }
        }
    }
}
