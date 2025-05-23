package com.example.composestability

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DontMemoize

@Composable
fun RememberedLambdaExample(
    state: ComponentState
) {
    val label = state.label
    LambdaConsumer {
        println(label)
    }
}

@Composable
fun NonRememberedLambdaExample(
    state: ComponentState
) {
    LambdaConsumer @DontMemoize {
        println(state.label)
    }
}

@Composable
private fun LambdaConsumer(
    lambda: (ComponentState) -> Unit
) {
    Text(lambda.toString())
}