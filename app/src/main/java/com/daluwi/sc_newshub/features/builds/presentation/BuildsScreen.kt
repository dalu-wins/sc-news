package com.daluwi.sc_newshub.features.builds.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.daluwi.sc_newshub.features.builds.presentation.components.BuildCard
import com.daluwi.sc_newshub.features.builds.presentation.components.BuildFAB
import com.daluwi.sc_newshub.features.builds.presentation.components.BuildTopBar

private const val SMALL_RADIUS: Int = 8
private const val BIG_RADIUS: Int = 24
private const val SPACING: Int = 6
private const val HORIZONTAL_PADDING: Int = 12
private const val VERTICAL_PADDING: Int = 12

@Composable
fun BuildsScreen(
    viewModel: BuildsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BuildTopBar() },
        floatingActionButton = { BuildFAB() }
    ) { contentPadding ->
        if (state.builds.isEmpty()) {
            Text(
                "Keine Builds gefunden.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(HORIZONTAL_PADDING.dp)
            )
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(SPACING.dp),
                contentPadding = PaddingValues(
                    top = contentPadding.calculateTopPadding() + VERTICAL_PADDING.dp,
                    bottom = contentPadding.calculateBottomPadding() + VERTICAL_PADDING.dp,
                    start = HORIZONTAL_PADDING.dp,
                    end = HORIZONTAL_PADDING.dp,
                )
            ) {
                itemsIndexed(state.builds) { index, build ->
                    val shape = when (index) {
                        0 -> RoundedCornerShape(
                            topStart = BIG_RADIUS.dp,
                            topEnd = BIG_RADIUS.dp,
                            bottomStart = SMALL_RADIUS.dp,
                            bottomEnd = SMALL_RADIUS.dp
                        )

                        state.builds.lastIndex -> RoundedCornerShape(
                            topStart = SMALL_RADIUS.dp,
                            topEnd = SMALL_RADIUS.dp,
                            bottomStart = BIG_RADIUS.dp,
                            bottomEnd = SMALL_RADIUS.dp
                        )

                        else -> RoundedCornerShape(SMALL_RADIUS.dp)
                    }

                    BuildCard(
                        build = build,
                        shape = shape
                    )
                }
                item {
                    Button(
                        onClick = { /***/ },
                        shape = RoundedCornerShape(
                            topStart = SMALL_RADIUS.dp,
                            topEnd = SMALL_RADIUS.dp,
                            bottomStart = BIG_RADIUS.dp,
                            bottomEnd = BIG_RADIUS.dp
                        )
                    ) {
                        Text("All patch notes on Spectrum")
                    }
                }
            }
        }
    }
}
