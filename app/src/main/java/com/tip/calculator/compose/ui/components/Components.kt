package com.tip.calculator.compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tip.calculator.compose.core.util.DP_4
import com.tip.calculator.compose.core.util.NORMAL_SPACING
import com.tip.calculator.compose.core.util.TRIPLE_SPACING
import androidx.compose.material3.CardElevation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text =  labelId) },
        leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney,
            contentDescription = "Money Icon")
        },
        singleLine = isSingleLine,
        textStyle = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .padding(start = NORMAL_SPACING, end = NORMAL_SPACING)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}


@Composable
fun RoundIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
) {
    Card(
        modifier = modifier.padding(NORMAL_SPACING)
            .clickable(onClick = onClick)
           ,
        shape = CircleShape,
    ) {
        Icon(imageVector, null, tint = tint)
    }
}