package me.bread.order.application.service

import me.bread.order.domain.entity.OrderItem
import me.bread.order.presentation.support.error.ErrorType
import me.bread.order.presentation.support.error.RestException
import org.springframework.stereotype.Component

@Component
class StockChecker(private val productService: ProductService) {
    fun verifyStock(orderItem: List<OrderItem>) {
        val outOfStockItems = orderItem.filterNot { productService.isProductQuantityEnough(it.id) }
        if (outOfStockItems.isNotEmpty()) {
            throw RestException(
                ErrorType.INVALID_ARG_ERROR,
                "One or more products do not have enough quantity",
            )
        }
    }
}
