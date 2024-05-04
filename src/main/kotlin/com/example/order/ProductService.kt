package com.example.order

class ProductService(
    private val productApi: ProductApi,
) {
    fun isProductQuantityEnough(itemId: Long) = productApi.isProductQuantityEnough(itemId)
}
