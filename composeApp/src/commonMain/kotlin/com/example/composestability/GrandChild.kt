package com.example.composestability

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GrandChild(
    stableParam: String,
    unstableParam: CallbackHandler,
    unstableParam2: List<OrderData>,
    unstableParam3: OrderData,
    lambda: (ComponentState) -> Unit,
    lambda2: (ComponentState) -> Unit,
    lambda3: (ComponentState) -> Unit
) {
    Column(
        Modifier
            .width(200.dp)
            .background(Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stableParam)
        Text(unstableParam.toString())
        Text(unstableParam2.toString())
        Text(unstableParam3.toString())
        Text(lambda.toString())
        Text(lambda2.toString())
        Text(lambda3.toString())
    }
}