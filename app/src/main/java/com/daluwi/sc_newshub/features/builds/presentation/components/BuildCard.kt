package com.daluwi.sc_newshub.features.builds.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daluwi.sc_newshub.features.builds.domain.models.Build

@Composable
fun BuildCard(
    build: Build,
    shape: RoundedCornerShape,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = shape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = build.channel.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${build.version.mainVersion}.${build.version.subVersion}.${build.version.patch}-${build.channel.name.lowercase()}.${build.build}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            FilledTonalIconButton(
                onClick = { /***/ }
            ) {
                Icon(Icons.Default.Public, "Visit spectrum for official patch notes.")
            }
        }
    }
}
