@file:OptIn(ExperimentalUuidApi::class)

package com.example.composestability

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

const val EXAMPLE_STRING = "some string"

val ExampleList = listOf(EXAMPLE_STRING)
val ExampleClass = CallbackHandler()
val ExampleDataClass = OrderData()

@Composable
fun ParentWithOneChild() {
    var parentState by remember {
        mutableIntStateOf(0)
    }
    Child(parentState.toString())
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(1.seconds)
            parentState += 1
        }
    }
}

@Composable
private fun Child(
    param: String,
) {
    Text(param)
    GrandChild(
        stableParam = EXAMPLE_STRING,
        unstableParam = ExampleClass,
        unstableParam2 = ExampleDataClass,
        listableParam = ExampleList
    )
}

@Composable
private fun GrandChild(
    stableParam: String,
    unstableParam: CallbackHandler,
    unstableParam2: OrderData,
    listableParam: List<String>,
) {
    Column(
        Modifier
            .size(200.dp)
            .background(Color.Gray)
    ) {
        Text(stableParam)
        Text(unstableParam.toString())
        Text(unstableParam2.toString())
        Text(listableParam.toString())
    }
}

class CallbackHandler {
    var handler: () -> Unit = {
        println("Callback triggered")
    }
    fun onCallback() {
        handler()
    }
}

data class OrderData(
    var orderId: String = Uuid.random().toString(),
    val orderUsers: List<User> = emptyList()
)

data class User(
    val username: String = "awesomeUser",
    var ordersCount: Int = 0
)
