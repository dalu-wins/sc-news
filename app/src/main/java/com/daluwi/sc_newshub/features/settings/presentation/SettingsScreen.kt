package com.daluwi.sc_newshub.features.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_newshub.core.theme.HORIZONTAL_PADDING
import com.daluwi.sc_newshub.core.theme.VERTICAL_PADDING
import com.daluwi.sc_newshub.features.settings.domain.models.Category
import com.daluwi.sc_newshub.features.settings.presentation.components.displaySection

private const val ITEM_SPACING: Int = 6

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val displaySettings = state.settings.filter { it.category == Category.Display }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { contentPadding ->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(ITEM_SPACING.dp),
            contentPadding = PaddingValues(
                top = VERTICAL_PADDING.dp,
                bottom = VERTICAL_PADDING.dp,
                start = HORIZONTAL_PADDING.dp,
                end = HORIZONTAL_PADDING.dp,
            )
        ) {

            // TODO noticeSection()

            displaySection(
                settings = displaySettings,
                onEvent = { event -> viewModel.onEvent(event) },
            )

        }
    }
}
