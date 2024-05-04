package com.example.order.domain.delivery

class DeliveryService(
    private val deliveryApi: DeliveryApi,
) {
    fun isSurChargeArea(postNum: String) = deliveryApi.fetchSurChargeArea().contains(postNum)
}
