package me.bread.order.domain.product

interface ProductApi {
    fun isProductQuantityEnough(itemId: Long): Boolean
}
