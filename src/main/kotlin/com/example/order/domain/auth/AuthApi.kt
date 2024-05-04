package com.example.order.domain.auth

interface AuthApi {
    fun fetchCustomerIdBy(token: String): Long
}
