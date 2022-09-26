package com.tip.calculator.compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.tip.calculator.compose.R

val SP_BODY_LARGE = 16.sp
val SP_BODY_MEDIUM = 14.sp
val SP_BODY_SMALL = 12.sp

val SP_HEADER_LARGE = 48.sp
val SP_HEADER_MEDIUM = 24.sp
val SP_HEADER_SMALL = 20.sp

val SP_LH_BODY_LARGE = 24.sp
val SP_LH_BODY_MEDIUM = 20.sp
val SP_LH_BODY_SMALL = 16.sp

val SP_LS_HALF = 0.5.sp


val mPlusRounded = FontFamily(
    Font(R.font.mplus_rounded1c_regular),
    Font(R.font.mplus_rounded1c_medium),
    Font(R.font.mplus_rounded1c_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = NormalTextStyle().copy(
        fontSize = SP_HEADER_LARGE
    ),
    headlineMedium = NormalTextStyle().copy(
        fontSize = SP_HEADER_MEDIUM
    ),
    headlineSmall = NormalTextStyle().copy(
        fontSize = SP_HEADER_SMALL
    ),
    bodyLarge = NormalTextStyle().copy(
        fontSize = SP_BODY_LARGE, lineHeight = SP_LH_BODY_LARGE, letterSpacing = SP_LS_HALF
    ),
    bodyMedium = NormalTextStyle().copy(
        fontSize = SP_BODY_MEDIUM
    ),
    bodySmall = NormalTextStyle().copy(
        fontSize = SP_BODY_SMALL
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */


)


fun NormalTextStyle(
    fontFamily: FontFamily = mPlusRounded,
    fontWeight: FontWeight = FontWeight.Normal,
): TextStyle {
    return TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
    )
}

