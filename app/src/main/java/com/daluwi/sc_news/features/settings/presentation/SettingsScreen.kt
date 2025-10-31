package com.daluwi.sc_news.features.settings.presentation

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
import com.daluwi.sc_news.core.theme.Dimensions
import com.daluwi.sc_news.features.settings.presentation.components.noticeSection
import com.daluwi.sc_news.features.settings.presentation.components.settingsSection

private const val ITEM_SPACING: Int = 6

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) { contentPadding ->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(ITEM_SPACING.dp),
            contentPadding = PaddingValues(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            )
        ) {

            settingsSection(
                state = state,
                onEvent = { event -> viewModel.onEvent(event) },
            )

            noticeSection()

        }
    }
}
