package com.daluwi.sc_newshub.presentation.live_builds

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.daluwi.sc_newshub.domain.models.LiveBuild

@Composable
fun LiveBuildsScreen(
    viewModel: LiveBuildsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (state.liveBuilds.isEmpty()) {
            Text(
                "Keine Builds gefunden.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.liveBuilds) { build ->
                    LiveBuildCard(build)
                }
            }
        }
    }
}

@Composable
fun LiveBuildCard(build: LiveBuild) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = build.channel.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Version: ${build.version.mainVersion}.${build.version.subVersion}.${build.version.patch}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Build: ${build.build}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
