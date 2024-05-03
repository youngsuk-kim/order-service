package com.example.order

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class Order(val orderItems: List<OrderItem>) {
    companion object {
        fun request(orderItems: List<OrderItem>): Order {
            return Order(orderItems)
        }
    }

    fun retrieveQuantity(id: Long) {
        this.orderItems.first { id == it.id }
            .quantity
    }

    fun totalQuantity(): Long {
        return this.orderItems.sumOf { orderItem -> orderItem.quantity }
    }
}

class AuthService(
    private val authApi: AuthApi,
) {
    fun getUserId(token: String) = authApi.getUserId(token)
}

interface AuthApi {
    fun getUserId(token: String): Long
}

class AuthFakeApi : AuthApi {
    override fun getUserId(token: String): Long {
        return 1
    }
}

class OrderItem(
    val id: Long,
    val productName: String,
    val productPrice: BigDecimal,
    val quantity: Long,
)

class OrderSteps : FeatureSpec({

    feature("주문 준비 스텝") {
        scenario("점원이 관리자에게 주문을 요청한다") {
            // Given
            val orderItems =
                listOf(
                    OrderItem(1L, "조던 덩크 하이", BigDecimal.valueOf(10_000), quantity = 3),
                    OrderItem(1L, "아디다스 루이비통 스니커즈", BigDecimal.valueOf(24_000), quantity = 1),
                )

            // When
            val sut = Order.request(orderItems)

            // Then
            sut.totalQuantity() shouldBe 4
        }

        scenario("관리자는 토큰 정보를 통해 손님의 신원을 파악한다") {
            // Given
            val token = "user-token"

            // When
            val userId = AuthService(AuthFakeApi()).getUserId(token)

            // Then
            userId shouldBe 1L
        }

        scenario("관리자는 주문한 물품의 재고가 충분한지 확인한다") {
            // 재고 확인 로직 추가
        }

        scenario("관리자는 결제 회사 직원이 업무 중인지 확인한다") {
            // 결제 직원의 상태 확인
        }

        scenario("관리자는 손님의 핸드폰 번호의 유효성을 검사한다") {
            // 번호 유효성 검증 로직 추가
        }

        scenario("관리자는 손님의 배송 주소를 확인한다") {
            // 배송 주소 검증 로직 추가
        }

        scenario("관리자는 주문 금액을 산출한다") {
            // 주문 총액 산출
        }
    }

    feature("결제 스텝") {
        scenario("관리자는 결제 직원에게 손님 정보, 주문 내역 정보, 카드 정보, 결제 금액을 건네준다") {
            // 결제 직원에게 정보 전달 확인
        }

        scenario("결제 직원은 결제를 완료했다고 통지한다") {
            // 결제 완료 여부 확인
        }

        scenario("관리자는 결제가 실패했을 경우를 대비한다") {
            // 결제 실패에 대한 대비
        }
    }

    feature("주문 완료 스텝") {
        scenario("시간 제한 주문이라면, 점원에게 주문 시간이 초과되었다고 알린다") {
            // 시간 초과에 대한 알림 로직
        }

        scenario("관리자는 결제 정보와 주문 정보가 일치하는지 확인한다") {
            // 정보 일치 확인
        }

        scenario("관리자는 재고 관리실에 아무도 못 들어오게 막는다") {
            // 재고 관리 제한
        }

        scenario("관리자는 재고를 감소시킨다") {
            // 재고 감소 로직 추가
        }

        scenario("관리자는 결제 정보를 통해 주문을 완료로 표시한다") {
            // 주문 완료 표시
        }

        scenario("택배 배송을 선택한 고객이라면, 배송 예정 표에 주문을 추가한다") {
            // 배송 예정 추가
        }

        scenario("고객이 선택한 배송 방식에 따라 배송을 준비한다") {
            // 배송 방식에 따른 배송 준비
        }

        scenario("고객에게 주문 완료 및 배송 준비 상황을 알린다") {
            // 주문 완료 및 배송 정보 알림
        }
    }
})
