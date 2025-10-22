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
import androidx.navigation.NavBackStackEntry
import com.daluwi.sc_newshub.core.theme.Dimensions
import com.daluwi.sc_newshub.features.settings.presentation.components.displaySection

private const val ITEM_SPACING: Int = 6

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    parentEntry: NavBackStackEntry? = null
) {
    val viewModel: SettingsViewModel = if (parentEntry != null) {
        hiltViewModel(parentEntry)
    } else {
        hiltViewModel()
    }

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

            // TODO noticeSection()

            displaySection(
                state = state,
                onEvent = { event -> viewModel.onEvent(event) },
            )

        }
    }
}
