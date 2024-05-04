package me.bread.order.domain.order

data class OrderCustomer(
    val orderId: Long,
    val customerEmail: String,
    val customerName: String,
)
