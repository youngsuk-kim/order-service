package me.bread.order.framework

import me.bread.order.domain.product.ProductApi

class ProductFakeApi : ProductApi {
    override fun isProductQuantityEnough(itemId: Long) = true
}
