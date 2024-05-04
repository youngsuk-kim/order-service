package com.example.order

interface ProductApi {
    fun isProductQuantityEnough(itemId: Long): Boolean
}
