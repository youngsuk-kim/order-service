package me.bread.order.domain.auth

interface AuthApi {
    fun fetchCustomerIdBy(token: String): Long
}
