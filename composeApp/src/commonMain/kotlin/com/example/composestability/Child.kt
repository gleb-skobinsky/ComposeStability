package com.example.composestability

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Child(
    state: ComponentState,
    unstableLambda: (ComponentState) -> Unit
) {
    Text(state.timerCounter.toString())

    GrandChild(
        stableParam = state.label,
        unstableParam = state.callbackHandler,
        unstableParam2 = state.orderData,
        unstableParam3 = state.singleOrder,
        unstableLambda = unstableLambda,
        unstableLambda2 = {
            println(it.label)
        },
        unstableLambda3 = {
            println(state.label)
        }
    )
}