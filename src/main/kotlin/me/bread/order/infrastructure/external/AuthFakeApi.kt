package me.bread.order.infrastructure.external

import me.bread.order.application.external.AuthApi
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("!live")
@Component
class AuthFakeApi : AuthApi {
    override fun fetchCustomerIdBy(token: String): Long = 1
}
