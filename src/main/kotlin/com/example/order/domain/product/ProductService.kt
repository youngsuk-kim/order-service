package com.example.order.domain.product

class ProductService(
    private val productApi: ProductApi,
) {
    fun isProductQuantityEnough(itemId: Long) = productApi.isProductQuantityEnough(itemId)
}
