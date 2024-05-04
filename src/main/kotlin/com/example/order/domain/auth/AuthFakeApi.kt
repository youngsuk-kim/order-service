package com.example.order.domain.auth

class AuthFakeApi : AuthApi {
    override fun fetchCustomerIdBy(token: String): Long = 1
}
