package me.bread.order.application.usecase

import me.bread.order.application.model.Customer
import me.bread.order.application.service.AuthService
import me.bread.order.application.service.DeliveryService
import me.bread.order.application.service.OrderService
import me.bread.order.application.service.ProductService
import me.bread.order.domain.entity.OrderItem
import org.springframework.stereotype.Component

@Component
class PreorderUseCase(
    private val authService: AuthService,
    private val orderService: OrderService,
    private val productService: ProductService,
    private val deliveryService: DeliveryService,
) {
    fun execute(customer: Customer, orderItems: List<OrderItem>) {
        // 고객 여부 확인
        authService.getCustomerId(customer.token)

        // 상품 재고 확인
        productService.verifyStock(orderItems)

        // 총 비용 게산
        orderService.calculateTotalCharge(
            deliveryService.isSurChargeArea(customer.customerPostalCode),
        )

        // 주문 아이템 생성
        orderService.preorder(orderItems)

        // 배송 전 저장
        deliveryService.preDelivery()
    }
}
