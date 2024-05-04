package com.example.order

import io.kotest.core.spec.style.FeatureSpec
import java.math.BigDecimal

class PaymentSteps : FeatureSpec(
    {
        feature("결제 스텝") {

            scenario("결제 직원은 완료된 결제 정보를 준다") {
                // Given
                val paymentProducts = listOf(
                    PaymentProduct(
                        productId = 1L,
                        name = "조던",
                        price = BigDecimal(100_000),
                        quantity = 1L,
                    ),
                )

                val orderCustomer = OrderCustomer(
                    orderId = 1L,
                    customerEmail = "example@gmail.com",
                    customerName = "김영석",
                )

                val paymentProductRepository = InMemoryDatabase.repository<Long, PaymentProduct>()
                val paymentProductIds = paymentProducts.map { paymentProductRepository.save(it) }

                val orderCustomerRepository = InMemoryDatabase.repository<Long, OrderCustomer>()
                val orderCustomerId = orderCustomerRepository.save(orderCustomer)

                // When

                // Then
            }
        }
    },
)
