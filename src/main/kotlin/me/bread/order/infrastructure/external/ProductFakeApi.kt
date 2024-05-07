package me.bread.order.infrastructure.external

import me.bread.order.application.external.ProductApi
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("!live")
@Component
class ProductFakeApi : ProductApi {
    override fun isProductQuantityEnough(itemId: Long) = true
}
