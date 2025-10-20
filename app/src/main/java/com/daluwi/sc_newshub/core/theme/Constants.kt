package com.daluwi.sc_newshub.core.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

const val CORNER_RADIUS_SMALL: Int = 8
const val CORNER_RADIUS_BIG: Int = 24
const val HORIZONTAL_PADDING: Int = 12
const val VERTICAL_PADDING: Int = 12
const val CARD_HORIZONTAL_PADDING: Int = 24
const val CARD_VERTICAL_PADDING: Int = 16
const val FAB_SPACING: Int = 64

val cardShapeStart = RoundedCornerShape(
    topStart = CORNER_RADIUS_BIG.dp,
    topEnd = CORNER_RADIUS_BIG.dp,
    bottomStart = CORNER_RADIUS_SMALL.dp,
    bottomEnd = CORNER_RADIUS_SMALL.dp
)

val cardShapeMiddle = RoundedCornerShape(size = CORNER_RADIUS_SMALL.dp)

val cardShapeEnd = RoundedCornerShape(
    topStart = CORNER_RADIUS_SMALL.dp,
    topEnd = CORNER_RADIUS_SMALL.dp,
    bottomStart = CORNER_RADIUS_BIG.dp,
    bottomEnd = CORNER_RADIUS_BIG.dp
)

val cardShapeSingle = RoundedCornerShape(size = CORNER_RADIUS_BIG.dp)