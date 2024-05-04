package com.example.order.domain.product

interface ProductApi {
    fun isProductQuantityEnough(itemId: Long): Boolean
}
