package me.bread.order.domain.delivery

class DeliveryFakeApi : DeliveryApi {
    override fun fetchSurChargeArea(): List<String> {
        return listOf("363")
    }
}
