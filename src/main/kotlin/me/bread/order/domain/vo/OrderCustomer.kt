package me.bread.order.domain.vo

data class OrderCustomer(
    val orderId: Long,
    val customerEmail: String,
    val customerName: String,
    val customerPostalCode: String,
    val customerDestination: String,
)
