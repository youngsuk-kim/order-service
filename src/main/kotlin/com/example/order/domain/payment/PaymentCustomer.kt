package com.example.order.domain.payment

import com.example.order.domain.order.OrderCustomer

class PaymentCustomer(
    val id: Long? = null,
    val orderId: Long,
    val customerEmail: String,
    val customerName: String,
) {
    companion object {
        fun create(orderCustomer: OrderCustomer) =
            PaymentCustomer(
                orderId = orderCustomer.orderId,
                customerEmail = orderCustomer.customerEmail,
                customerName = orderCustomer.customerName,
            )
    }
}
