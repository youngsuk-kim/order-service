package me.bread.order.domain.payment

data class Payment(
    val customerPaymentId: Long,
    val payProductId: Long,
)
