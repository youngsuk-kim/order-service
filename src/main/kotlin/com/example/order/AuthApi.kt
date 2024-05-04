package com.example.order

interface AuthApi {
    fun fetchCustomerIdBy(token: String): Long
}
