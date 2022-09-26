package com.tip.calculator.compose.ui

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tip.calculator.compose.R
import androidx.compose.ui.tooling.preview.Preview
import com.tip.calculator.compose.core.util.*
import com.tip.calculator.compose.ui.components.InputField
import com.tip.calculator.compose.ui.components.RoundIconButton


@Composable
fun TopHeader(modifier: Modifier = Modifier, pricePerPerson: Float = 0f) {
    Surface(
        modifier = modifier
            .fillMaxWidth(PERCENT_100)
            .height(DP_MEDIUM_HEADER)
            .clip(shape = roundedCornerShape())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.price_per_person),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = stringResource(id = R.string.price, pricePerPerson),
                style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
@Preview(showBackground = false)
fun MainContent(modifier: Modifier = Modifier) {

    val totalPricePerPersonState = remember {
        mutableStateOf(0.0f)
    }

    val tipAmountState = remember {
        mutableStateOf(0.0f)
    }

    val splitByValue = remember {
        mutableStateOf(1)
    }

    val totalPriceState = remember { mutableStateOf("") }
    val validState = remember(totalPriceState.value) {
        totalPriceState.value.trim().isNotEmpty()
    }

    val sliderValueState = remember {
        mutableStateOf(0.0f)
    }
    val tipPercentage: Int = (sliderValueState.value * 100).toInt()


    PriceForm(
        totalPriceState = totalPriceState,
        validState = validState,
        splitByValue = splitByValue,
        tipAmountState = tipAmountState,
        sliderValueState = sliderValueState,
        tipPercentage = tipPercentage,
        totalPricePerPersonState = totalPricePerPersonState
    ) { price ->
        Log.d("Price", "Price is $price")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PriceForm(
    splitByValue: MutableState<Int>,
    tipAmountState: MutableState<Float>,
    totalPricePerPersonState: MutableState<Float>,
    totalPriceState: MutableState<String>,
    sliderValueState: MutableState<Float>,
    tipPercentage: Int,
    validState: Boolean,
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = modifier
            .padding(NORMAL_SPACING)
            .fillMaxWidth(PERCENT_100)
            .clip(shape = roundedCornerShape(DP_8))
            .border(border = borderStroke())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopHeader(pricePerPerson = totalPricePerPersonState.value)
            InputField(
                valueState = totalPriceState,
                labelId = stringResource(id = R.string.enter_total_price),
                enabled = true, onAction =
                KeyboardActions() {
                    if (!validState) return@KeyboardActions
                    onValueChanged(totalPriceState.value.trim())
                    keyboardController?.hide() ?: return@KeyboardActions
                }
            )
            if (validState) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        horizontalArrangement = Arrangement.Start, modifier = modifier.padding(
                            NORMAL_SPACING
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.split_over),
                            modifier = modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(Modifier.weight(1f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.padding(NORMAL_SPACING),
                            horizontalArrangement = Arrangement.End
                        ) {
                            RoundIconButton(imageVector = Icons.Default.Remove, onClick = {
                                splitByValue.value =
                                    if (splitByValue.value - 1 > 0) splitByValue.value - 1 else 1
                                totalPricePerPersonState.value = calculateTotalPricePerPerson(
                                    totalPriceState.value.toFloat(),
                                    splitByValue.value,
                                    tipPercentage.toFloat() / 100
                                )
                            })
                            Text(
                                text = stringResource(
                                    id = R.string.split_count,
                                    splitByValue.value
                                ),
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = modifier.align(Alignment.CenterVertically)
                            )
                            RoundIconButton(
                                imageVector = Icons.Default.Add,
                                onClick = {
                                    splitByValue.value++
                                    totalPricePerPersonState.value = calculateTotalPricePerPerson(
                                        totalPriceState.value.toFloat(),
                                        splitByValue.value,
                                        tipPercentage.toFloat() / 100
                                    )
                                })
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.Start, modifier = modifier.padding(
                            NORMAL_SPACING
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.tip),
                            modifier = modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = stringResource(id = R.string.tip_amount, tipAmountState.value),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = DOUBLE_SPACING)
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.tip_percentage, tipPercentage),
                        modifier = modifier.align(Alignment.CenterHorizontally)
                    )

                    Slider(
                        value = sliderValueState.value,
                        modifier = modifier.padding(
                            NORMAL_SPACING
                        ),
                        steps = 4,
                        onValueChangeFinished = {


                        },
                        onValueChange = {
                            sliderValueState.value = it
                            tipAmountState.value =
                                calculateTotalTip(totalPriceState.value.toFloat(), it)

                            totalPricePerPersonState.value = calculateTotalPricePerPerson(
                                totalPrice = totalPriceState.value.toFloat(),
                                splitByBuyer = splitByValue.value,
                                tipPercentage = it
                            )
                        })
                }

            }

        }
    }
}

private fun calculateTotalTip(totalPrice: Float, tipPercentage: Float): Float {
    return if (totalPrice > 1 && totalPrice.toString().isNotEmpty())
        (totalPrice * tipPercentage) * 100 / 100 else 0.0f
}

private fun calculateTotalPricePerPerson(
    totalPrice: Float,
    splitByBuyer: Int, tipPercentage: Float
): Float {
    val tip = calculateTotalTip(totalPrice, tipPercentage)
    return (totalPrice + tip) / splitByBuyer
}