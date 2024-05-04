package com.example.order

import com.example.order.domain.payment.PaymentCustomer
import com.example.order.domain.payment.PaymentProduct
import io.kotest.core.spec.style.FeatureSpec
import java.math.BigDecimal

class PaymentFeatureTest : FeatureSpec(
    {
        feature("결제") {

            scenario("완료된 결제 이력과 주문 내용을 비교 한다") {
                // Given
                val paymentProducts =
                    listOf(
                        PaymentProduct(
                            productId = 1L,
                            name = "조던",
                            price = BigDecimal(100_000),
                            quantity = 1L,
                        ),
                    )

                val paymentCustomer =
                    PaymentCustomer(
                        orderId = 1L,
                        customerEmail = "example@example.com",
                        customerName = "김영석",
                    )

                // When


                // Then
            }
        }
    },
)
