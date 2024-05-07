package me.bread.order.domain.entity

data class Payment(
    val customerPaymentId: Long,
    val payProductId: Long,
)
