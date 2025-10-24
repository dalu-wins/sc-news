package com.daluwi.sc_news.features.settings.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.daluwi.sc_news.R
import com.daluwi.sc_news.core.theme.Dimensions

private const val SPACING: Int = 16

private const val IMAGE_SIZE: Int = 112
private const val IMAGE_PADDING: Int = 8

private const val HORIZONTAL_PADDING: Int = 8

fun LazyListScope.noticeSection() {
    item {
        Text(
            text = "Notice & Disclaimers",
            modifier = Modifier.padding(
                vertical = Dimensions.VERTICAL_PADDING.dp,
                horizontal = Dimensions.HORIZONTAL_PADDING.dp
            ),
            style = MaterialTheme.typography.titleMedium,
        )
    }

    item {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACING.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HORIZONTAL_PADDING.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = HORIZONTAL_PADDING.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(SPACING.dp),
            ) {
                Image(
                    modifier = Modifier
                        .size(size = IMAGE_SIZE.dp)
                        .padding(all = IMAGE_PADDING.dp),
                    painter = painterResource(id = R.drawable.community_badge),
                    contentDescription = "'Made by the Community' - icon",
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colorScheme.outline
                    )
                )
                Text(
                    text = "This is an unofficial Star Citizen fan project, " +
                            "not affiliated with the Cloud Imperium group of companies. " +
                            "All content on this app not authored by its developers or " +
                            "users are property of their respective owners.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                )

            }

            Text(
                text = "Star Citizen®, Roberts Space Industries® and Cloud Imperium ® are registered trademarks of Cloud Imperium Rights LLC",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }

}