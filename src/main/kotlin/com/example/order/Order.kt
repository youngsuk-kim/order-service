package com.example.order

class Order(private val orderItems: List<OrderItem>) {
    companion object {
        fun request(orderItems: List<OrderItem>) = Order(orderItems)
    }

    fun totalQuantity() = this.orderItems.sumOf { orderItem -> orderItem.quantity }

    fun retrieveQuantity(id: Long) = this.orderItems.first { id == it.id }.quantity

    fun charge() = this.orderItems.sumOf { orderItem -> orderItem.price }
}
