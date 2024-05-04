package com.example.order

class AuthFakeApi : AuthApi {
    override fun fetchCustomerIdBy(token: String): Long = 1
}
