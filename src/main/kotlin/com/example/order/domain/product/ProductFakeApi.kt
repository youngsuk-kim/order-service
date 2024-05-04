package com.example.order.domain.product

class ProductFakeApi : ProductApi {
    override fun isProductQuantityEnough(itemId: Long) = true
}
