package com.tip.calculator.compose.core.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.tip.calculator.compose.ui.theme.darkGrey
import com.tip.calculator.compose.ui.theme.lightGrey


fun roundedCornerShape(cornerRadius: Dp = DP_12): RoundedCornerShape {
    return RoundedCornerShape(cornerRadius)
}

fun borderStroke(width: Dp = DP_2, color: Color = darkGrey): BorderStroke {
    return BorderStroke(width = width, color = color)
}

