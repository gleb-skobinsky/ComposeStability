package com.example.composestability

data class ComponentState(
    val timerCounter: Int = 0,
    val label: String = EXAMPLE_STRING,
    val singleOrder: OrderData = OrderData(),
    //val orderData: List<OrderData> = listOf(OrderData()),
    val callbackHandler: CallbackHandler = CallbackHandler(),
)