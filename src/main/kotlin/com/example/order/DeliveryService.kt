package com.example.order

class DeliveryService(
    private val deliveryApi: DeliveryApi,
) {
    fun isSurChargeArea(postNum: String) = deliveryApi.fetchSurChargeArea().contains(postNum)
}
