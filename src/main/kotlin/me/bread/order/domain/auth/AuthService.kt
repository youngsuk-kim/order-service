package me.bread.order.domain.auth

class AuthService(
    private val authApi: AuthApi,
) {
    fun getCustomerId(token: String) = authApi.fetchCustomerIdBy(token)
}
