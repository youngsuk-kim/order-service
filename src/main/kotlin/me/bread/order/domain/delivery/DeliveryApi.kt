package me.bread.order.domain.delivery

interface DeliveryApi {
    fun fetchSurChargeArea(): List<String>
}
