package me.bread.order

import me.bread.order.domain.order.OrderItem
import java.math.BigDecimal

fun orderItems() = listOf(
    OrderItem(
        id = 1L,
        productName = "조던 덩크 하이",
        productPrice = BigDecimal.valueOf(10_000),
        quantity = 3,
        price = BigDecimal.valueOf(100_000),
    ),
    OrderItem(
        id = 1L,
        productName = "아디다스 루이비통 스니커즈",
        productPrice = BigDecimal.valueOf(24_000),
        quantity = 1,
        price = BigDecimal.valueOf(202_000),
    ),
)