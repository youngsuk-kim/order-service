package com.example.order

import com.example.order.domain.auth.AuthFakeApi
import com.example.order.domain.auth.AuthService
import com.example.order.domain.common.PhoneNumber
import com.example.order.domain.delivery.DeliveryFakeApi
import com.example.order.domain.delivery.DeliveryService
import com.example.order.domain.order.Order
import com.example.order.domain.order.OrderCustomer
import com.example.order.domain.order.OrderItem
import com.example.order.domain.payment.PaymentCustomer
import com.example.order.domain.product.ProductFakeApi
import com.example.order.domain.product.ProductService
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class PreorderFeatureTest : FeatureSpec(
    {

        feature("주문 준비") {
            scenario("점원이 관리자에게 주문을 요청한다") {
                // Given
                val orderItems = orderItems()

                // When
                val sut = Order.request(orderItems)

                // Then
                sut.totalQuantity() shouldBe 4
            }

            scenario("관리자는 토큰 정보를 통해 손님의 신원을 파악한다") {
                // Given
                val token = "customer-token"

                // When
                val sut = AuthService(AuthFakeApi()).getCustomerId(token)

                // Then
                sut shouldBe 1L
            }

            scenario("관리자는 주문한 물품의 재고가 충분한지 확인한다") {
                // Given
                val itemId = 1L

                // When
                val sut = ProductService(ProductFakeApi()).isProductQuantityEnough(itemId)

                // Then
                sut shouldBe true
            }

            scenario("관리자는 손님의 핸드폰 번호의 유효성을 검사한다") {
                // Given When
                val sut = PhoneNumber("01030202322")

                // Then
                sut.number shouldBe "01030202322"
            }

            scenario("관리자는 손님의 배송지가 도서 산간 지역인지 확인한다") {
                // Given
                val postNum = "363"

                // When
                val sut = DeliveryService(DeliveryFakeApi()).isSurChargeArea(postNum)

                // Then
                sut shouldBe true
            }

            scenario("관리자는 주문 금액을 산출한다") {
                // Given
                val orderItems = orderItems()

                // When
                val sut = Order.request(orderItems).charge()

                // Then
                sut shouldBe BigDecimal(302_000)
            }

            scenario("관리자는 결제 정보를 받아서 저장한다") {
                // Given
                val orderPay =
                    OrderCustomer(
                        orderId = 1L,
                        customerEmail = "example@gmail.com",
                        customerName = "김영석",
                    )

                // When
                val sut = PaymentCustomer.create(orderPay)

                // Then
                with(sut) {
                    orderId shouldBe 1L
                    customerName shouldBe "김영석"
                    customerEmail shouldBe "example@gmail.com"
                }
            }
        }
    },
)

private fun orderItems() = listOf(
    OrderItem(
        id = 1L,
        productName = "조던 덩크 하이",
        productPrice = BigDecimal.valueOf(10_000),
        quantity = 3,
        price = BigDecimal.valueOf(100_000),
    ),
    OrderItem(
        id = 1L,
        productName = "아디다스 루이비통 스니커즈",
        productPrice = BigDecimal.valueOf(24_000),
        quantity = 1,
        price = BigDecimal.valueOf(202_000),
    ),
)
