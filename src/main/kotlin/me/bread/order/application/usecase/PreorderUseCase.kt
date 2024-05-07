package me.bread.order.application.usecase

import me.bread.order.application.model.Customer
import me.bread.order.application.service.AuthService
import me.bread.order.application.service.OrderService
import me.bread.order.application.service.StockChecker
import me.bread.order.domain.entity.OrderItem
import org.springframework.stereotype.Component

@Component
class PreorderUseCase(
    private val authService: AuthService,
    private val orderService: OrderService,
    private val stockChecker: StockChecker,
) {
    fun execute(customer: Customer, orderItem: List<OrderItem>) {
        // 고객 여부 확인
        authService.getCustomerId(customer.token)

        // 상품 재고 확인
        stockChecker.verifyStock(orderItem)

        // 주문 아이템 생성
        orderService.preorder(orderItem)

        // 배송 정보 생성
    }
}
