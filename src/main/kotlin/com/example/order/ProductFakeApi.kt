package com.example.order

class ProductFakeApi : ProductApi {
    override fun isProductQuantityEnough(itemId: Long) = true
}
