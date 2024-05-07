package me.bread.order.application.service

import io.kotest.core.spec.style.StringSpec
import me.bread.order.infrastructure.external.ProductFakeApi
import me.bread.order.orderItems
import org.junit.jupiter.api.assertDoesNotThrow

class StockCheckerTest : StringSpec(
    {
        "주문 상품의 재고가 있다면 예외를 던지지 않는다" {
            // Given When Then
            assertDoesNotThrow {
                StockChecker(ProductService(ProductFakeApi()))
                    .verifyStock(orderItems())
            }
        }
    },
)
