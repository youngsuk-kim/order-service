package me.bread.order.usecase

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import me.bread.order.application.service.AuthService
import me.bread.order.application.service.DeliveryService
import me.bread.order.application.service.ProductService
import me.bread.order.domain.entity.Order
import me.bread.order.domain.entity.PaymentCustomer
import me.bread.order.domain.vo.OrderCustomer
import me.bread.order.domain.vo.PhoneNumber
import me.bread.order.infrastructure.external.AuthFakeApi
import me.bread.order.infrastructure.external.DeliveryFakeApi
import me.bread.order.infrastructure.external.ProductFakeApi
import me.bread.order.orderItems
import java.math.BigDecimal

class PreorderUseCaseTest : FeatureSpec(
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
                val sut = AuthService(
                    AuthFakeApi(),
                )
                    .getCustomerId(token)

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
                        customerPostalCode = "363",
                        customerDestination = "땡땡 아파트 101동 1005호",
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
