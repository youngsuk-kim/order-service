package me.bread.order.infrastructure.external

import me.bread.order.application.external.DeliveryApi
import org.springframework.stereotype.Component

@Component
class DeliveryFakeApi : DeliveryApi {
    override fun fetchSurChargeArea(): List<String> {
        return listOf("363")
    }
}
