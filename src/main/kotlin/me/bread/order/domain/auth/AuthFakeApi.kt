package me.bread.order.domain.auth

class AuthFakeApi : me.bread.order.domain.auth.AuthApi {
    override fun fetchCustomerIdBy(token: String): Long = 1
}
