package me.bread.order.framework

import me.bread.order.domain.delivery.DeliveryApi

class DeliveryFakeApi : DeliveryApi {
    override fun fetchSurChargeArea(): List<String> {
        return listOf("363")
    }
}
