package me.bread.order.application.service

import me.bread.order.application.external.ProductApi
import org.springframework.stereotype.Component

@Component
class ProductService(
    private val productApi: ProductApi,
) {
    fun isProductQuantityEnough(itemId: Long) = productApi.isProductQuantityEnough(itemId)
}
