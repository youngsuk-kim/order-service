package com.example.order

class DeliveryFakeApi : DeliveryApi {
    override fun fetchSurChargeArea(): List<String> {
        return listOf("363")
    }
}
