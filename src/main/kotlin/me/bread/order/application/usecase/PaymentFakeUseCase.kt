package me.bread.order.application.usecase

import me.bread.order.infrastructure.external.MockPaymentApi
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("local", "local-dev")
@Component
class PaymentFakeUseCase {
    fun execute() {
        MockPaymentApi.sendPaymentRequest(
            orderId = "test-order-id",
            amount = "1.0",
            paymentKey = "test-key",
            successUrl = "http://localhost:8080/api/payments/confirm",
        )
    }
}
