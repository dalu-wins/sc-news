package com.daluwi.sc_news.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp


object Shapes {
    object Card {
        private const val CORNER_RADIUS_BIG: Int = 24
        private const val CORNER_RADIUS_SMALL: Int = 4

        const val HORIZONTAL_PADDING: Int = 24
        const val VERTICAL_PADDING: Int = 16

        val Start = RoundedCornerShape(
            topStart = CORNER_RADIUS_BIG.dp,
            topEnd = CORNER_RADIUS_BIG.dp,
            bottomStart = CORNER_RADIUS_SMALL.dp,
            bottomEnd = CORNER_RADIUS_SMALL.dp
        )

        val Middle = RoundedCornerShape(size = CORNER_RADIUS_SMALL.dp)

        val End = RoundedCornerShape(
            topStart = CORNER_RADIUS_SMALL.dp,
            topEnd = CORNER_RADIUS_SMALL.dp,
            bottomStart = CORNER_RADIUS_BIG.dp,
            bottomEnd = CORNER_RADIUS_BIG.dp
        )

        val Single = RoundedCornerShape(size = CORNER_RADIUS_BIG.dp)
    }

    object Badge {
        private const val CORNER_RADIUS_BIG: Int = 24
        private const val CORNER_RADIUS_SMALL: Int = 2

        val Single = RoundedCornerShape(size = CORNER_RADIUS_BIG.dp)

        val Left = RoundedCornerShape(
            topStart = CORNER_RADIUS_BIG.dp,
            topEnd = CORNER_RADIUS_SMALL.dp,
            bottomStart = CORNER_RADIUS_BIG.dp,
            bottomEnd = CORNER_RADIUS_SMALL.dp
        )

        val Right = RoundedCornerShape(
            topStart = CORNER_RADIUS_SMALL.dp,
            topEnd = CORNER_RADIUS_BIG.dp,
            bottomStart = CORNER_RADIUS_SMALL.dp,
            bottomEnd = CORNER_RADIUS_BIG.dp
        )

    }
}