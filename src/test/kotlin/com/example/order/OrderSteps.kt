package com.example.order

import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class Order(private val orderItems: List<OrderItem>) {
    companion object {
        fun request(orderItems: List<OrderItem>) = Order(orderItems)
    }

    fun totalQuantity() = this.orderItems.sumOf { orderItem -> orderItem.quantity }

    fun retrieveQuantity(id: Long) = this.orderItems.first { id == it.id }.quantity
}

class AuthService(
    private val authApi: AuthApi,
) {
    fun getUserId(token: String) = authApi.fetchUserIdBy(token)
}

interface AuthApi {
    fun fetchUserIdBy(token: String): Long
}

class AuthFakeApi : AuthApi {
    override fun fetchUserIdBy(token: String): Long = 1
}

class ProductService(
    private val productApi: ProductApi,
) {
    fun isProductQuantityEnough(itemId: Long) = productApi.isProductQuantityEnough(itemId)
}

interface ProductApi {
    fun isProductQuantityEnough(itemId: Long): Boolean
}

class ProductFakeApi : ProductApi {
    override fun isProductQuantityEnough(itemId: Long) = true
}

class OrderItem(
    val id: Long,
    val productName: String,
    val productPrice: BigDecimal,
    val quantity: Long,
)

data class PhoneNumber(val number: String) {
    init {
        require(isValidPhoneNumber(number)) { "Invalid phone number: $number" }
    }

    companion object {
        // 한국 휴대폰 번호 형식에 대한 정규식 패턴 (하이픈 없이)
        private val PHONE_NUMBER_PATTERN = Regex("^01[016789]\\d{7,8}$")

        fun isValidPhoneNumber(number: String): Boolean {
            return PHONE_NUMBER_PATTERN.matches(number)
        }
    }

    override fun toString(): String {
        // 보기 좋게 하이픈을 추가하여 출력하고 싶은 경우 사용
        return "${number.substring(0, 3)}-${number.substring(3, number.length - 4)}-${number.takeLast(4)}"
    }
}

class DeliveryService(
    private val deliveryApi: DeliveryApi,
) {
    fun isSurChargeArea(postNum: String) = deliveryApi.fetchSurChargeArea().contains(postNum)
}

interface DeliveryApi {
    fun fetchSurChargeArea(): List<String>
}

class DeliveryFakeApi : DeliveryApi {
    override fun fetchSurChargeArea(): List<String> {
        return listOf("363")
    }
}

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
            val requestOrder = Order.request(orderItems)

            // Then
            requestOrder.totalQuantity() shouldBe 4
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
            // Given
            val itemId = 1L

            // When
            val quantityEnough = ProductService(ProductFakeApi()).isProductQuantityEnough(itemId)

            // Then
            quantityEnough shouldBe true
        }

        scenario("관리자는 손님의 핸드폰 번호의 유효성을 검사한다") {
            // Given When
            val phoneNumber = PhoneNumber("01030202322")

            // Then
            phoneNumber.number shouldBe "01030202322"
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
