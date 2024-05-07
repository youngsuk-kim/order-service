package me.bread.order.framework

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

object MockPaymentApi {
    fun sendPaymentRequest(
        orderId: String,
        amount: String,
        successUrl: String,
        paymentKey: String,
    ) {
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val params = mapOf(
            "paymentKey" to paymentKey,
            "orderId" to orderId,
            "amount" to amount,
        )
        val entity = HttpEntity(params, headers)

        try {
            val response = RestTemplate().postForObject(successUrl, entity, String::class.java)
            println("Response from payment endpoint: $response")
        } catch (e: Exception) {
            println("Error during payment request: ${e.message}")
        }
    }
}
