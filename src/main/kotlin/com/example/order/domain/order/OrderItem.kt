package com.example.order.domain.order

import java.math.BigDecimal

class OrderItem(
    val id: Long,
    val productName: String,
    val productPrice: BigDecimal,
    val quantity: Long,
    val price: BigDecimal,
)