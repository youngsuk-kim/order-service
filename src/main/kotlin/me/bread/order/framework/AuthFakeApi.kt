package me.bread.order.framework

import me.bread.order.domain.auth.AuthApi

class AuthFakeApi : AuthApi {
    override fun fetchCustomerIdBy(token: String): Long = 1
}
