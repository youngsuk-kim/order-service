package me.bread.order.domain.entity

import me.bread.order.domain.vo.OrderCustomer

class PaymentCustomer(
    val id: Long? = null,
    val orderId: Long,
    val customerEmail: String,
    val customerName: String,
) {
    companion object {
        fun create(orderCustomer: OrderCustomer) = PaymentCustomer(
            orderId = orderCustomer.orderId,
            customerEmail = orderCustomer.customerEmail,
            customerName = orderCustomer.customerName,
        )
    }
}
