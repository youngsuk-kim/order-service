package me.bread.order.presentation.rest.request

import me.bread.order.application.model.Customer
import me.bread.order.domain.entity.OrderItem
import java.math.BigDecimal

data class PreorderRequest(
    val customer: CustomerRequest,
    val orderItems: List<OrderItemRequest>,

) {
    data class CustomerRequest(
        val token: String,
        val customerEmail: String,
        val customerName: String,
        val customerPostalCode: String,
        val customerAddressDetail: String,
    ) {
        fun toCustomer(): Customer {
            return Customer(
                token = this.token,
                customerEmail = this.customerEmail,
                customerName = this.customerName,
                customerPostalCode = this.customerPostalCode,
                customerAddressDetail = this.customerAddressDetail,
            )
        }
    }

    data class OrderItemRequest(
        val productItemId: Long,
        val productName: String,
        val productPrice: BigDecimal,
        val quantity: Long,
        val price: BigDecimal,
    ) {
        fun toOrderItem(): OrderItem {
            return OrderItem(
                productItemId = this.productItemId,
                productName = this.productName,
                productPrice = this.productPrice,
                quantity = this.quantity,
                price = this.price,
            )
        }
    }
}
