package com.example.composestability

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class ComponentState(
    val timerCounter: Int = 0,
    val label: String = EXAMPLE_STRING,
    val orderData: List<OrderData> = listOf(OrderData()),
    val callbackHandler: CallbackHandler = CallbackHandler(),
)

class SyntheticViewModel {
    private val _state = MutableStateFlow(ComponentState())
    val state = _state.asStateFlow()

    private val scope = CoroutineScope(
        Dispatchers.Main.immediate + SupervisorJob()
    )

    init {
        scope.launch {
            while (isActive) {
                delay(1.seconds)
                _state.update {
                    it.copy(timerCounter = it.timerCounter + 1)
                }
            }
        }
    }

    fun cancelScope() {
        scope.cancel()
    }
}

@Composable
fun ParentWithOneChild2() {
    val viewModel = remember { SyntheticViewModel() }
    val state by viewModel.state.collectAsState()
    Child(state)

    DisposableEffect(viewModel) {
        onDispose {
            viewModel.cancelScope()
        }
    }
}

@Composable
private fun Child(
    state: ComponentState,
) {
    Text(state.timerCounter.toString())
    GrandChild(
        stableParam = state.label,
        unstableParam = state.callbackHandler,
        unstableParam2 = state.orderData
    )
}

@Composable
private fun GrandChild(
    stableParam: String,
    unstableParam: CallbackHandler,
    unstableParam2: List<OrderData>,
) {
    Column(
        Modifier
            .size(200.dp)
            .background(Color.Gray)
    ) {
        Text(stableParam)
        Text(unstableParam.toString())
        Text(unstableParam2.toString())
    }
}
