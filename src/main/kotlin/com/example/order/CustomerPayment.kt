package com.example.order

class CustomerPayment(
    val id: Long? = null,
    val orderId: Long,
    val customerEmail: String,
    val customerName: String,
) {
    companion object {
        fun create(orderCustomer: OrderCustomer) = CustomerPayment(
            orderId = orderCustomer.orderId,
            customerEmail = orderCustomer.customerEmail,
            customerName = orderCustomer.customerName,
        )
    }
}
