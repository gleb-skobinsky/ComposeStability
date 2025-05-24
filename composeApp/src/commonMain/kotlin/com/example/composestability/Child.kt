package com.example.composestability

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Child(
    state: ComponentState,
    lambda: (ComponentState) -> Unit
) {
    val label = state.label
    Text(state.timerCounter.toString())

    GrandChild(
        stableParam = state.label,
        unstableParam = state.callbackHandler,
        unstableParam2 = state.orderData,
        unstableParam3 = state.singleOrder,
        lambda = lambda,
        lambda2 = {
            println(it.label)
        },
        lambda3 = {
            println(label)
        }
    )
}