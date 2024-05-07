package me.bread.order.application.usecase

import me.bread.order.application.external.AuthApi
import me.bread.order.application.service.StockChecker
import me.bread.order.domain.entity.Order
import me.bread.order.domain.entity.OrderItem
import org.springframework.stereotype.Component

@Component
class PreorderUseCase(
    private val authApi: AuthApi,
    private val stockChecker: StockChecker,
) {
    fun execute(token: String, orderItem: List<OrderItem>) {
        // 주문 아이템 생성
        Order.request(orderItem)

        // 고객 정보 조회
        authApi.fetchCustomerIdBy(token)

        // 상품 재고 확인
        stockChecker.verifyStock(orderItem)
    }
}
