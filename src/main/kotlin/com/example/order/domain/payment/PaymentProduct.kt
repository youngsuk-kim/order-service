package com.example.order.domain.payment

import java.math.BigDecimal

data class PaymentProduct(
    val productId: Long,
    val name: String,
    val price: BigDecimal,
    val quantity: Long,
)
