package com.example.composestability

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ParentWithOneChild2() {
    val viewModel: SyntheticViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    Child(state, viewModel.orderData) {
        println(state.label)
    }
}
