package com.example.order

class AuthService(
    private val authApi: AuthApi,
) {
    fun getCustomerId(token: String) = authApi.fetchCustomerIdBy(token)
}
