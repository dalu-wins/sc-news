package com.daluwi.sc_newshub.features.patches.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_newshub.core.theme.FAB_SPACING
import com.daluwi.sc_newshub.core.theme.HORIZONTAL_PADDING
import com.daluwi.sc_newshub.core.theme.VERTICAL_PADDING
import com.daluwi.sc_newshub.features.patches.presentation.components.PatchFAB
import com.daluwi.sc_newshub.features.patches.presentation.components.otherSection
import com.daluwi.sc_newshub.features.patches.presentation.components.pinnedSection

private const val ITEM_SPACING: Int = 6

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PatchScreen(
    viewModel: PatchViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val pinnedPatches = state.patches.filter { it.pinned }
    val otherPatches = state.patches.filter { !it.pinned }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = { PatchFAB { event -> viewModel.onEvent(event) } }
    ) { contentPadding ->
        if (state.patches.isEmpty()) {
            Text(
                text = "No patches found.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(HORIZONTAL_PADDING.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(ITEM_SPACING.dp),
                contentPadding = PaddingValues(
                    vertical = VERTICAL_PADDING.dp,
                    horizontal = HORIZONTAL_PADDING.dp,
                )
            ) {

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
                    Spacer(Modifier.height(FAB_SPACING.dp + contentPadding.calculateBottomPadding()))
                }
            }

        }
    }
}
