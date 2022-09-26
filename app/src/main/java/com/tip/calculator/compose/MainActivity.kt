package com.tip.calculator.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tip.calculator.compose.ui.MainContent
import com.tip.calculator.compose.ui.TopHeader
import com.tip.calculator.compose.ui.theme.TipCalculatorComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(content = {
             //   TopHeader(pricePerPerson = 40f)
                MainContent()
            }, modifier = Modifier)
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit, modifier: Modifier) {
    TipCalculatorComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
        ) {
            content()
        }
    }
}