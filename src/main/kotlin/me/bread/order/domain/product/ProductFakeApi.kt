package me.bread.order.domain.product

class ProductFakeApi : ProductApi {
    override fun isProductQuantityEnough(itemId: Long) = true
}
