package com.example.composestability

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SyntheticViewModel : ViewModel() {
    private val _state = MutableStateFlow(ComponentState())
    val state = _state.asStateFlow()

    private val _orderData = mutableStateListOf<OrderData>()
    val orderData: List<OrderData> get() = _orderData

    init {
        viewModelScope.launch {
            while (isActive) {
                delay(1.seconds)
                _state.update {
                    it.copy(
                        timerCounter = it.timerCounter + 1,
                    )
                }
                if (_state.value.timerCounter == 3) {
                    _orderData.add(OrderData())
                }
            }
        }
    }
}
