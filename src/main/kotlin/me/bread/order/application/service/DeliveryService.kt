package me.bread.order.application.service

import me.bread.order.application.external.DeliveryApi
import org.springframework.stereotype.Component

@Component
class DeliveryService(
    private val deliveryApi: DeliveryApi,
) {
    fun isSurChargeArea(postNum: String) = deliveryApi.fetchSurChargeArea().contains(postNum)

    fun preDelivery() {}
}
