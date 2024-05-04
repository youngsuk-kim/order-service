package me.bread.order.domain.auth

class AuthService(
    private val authApi: me.bread.order.domain.auth.AuthApi,
) {
    fun getCustomerId(token: String) = authApi.fetchCustomerIdBy(token)
}
