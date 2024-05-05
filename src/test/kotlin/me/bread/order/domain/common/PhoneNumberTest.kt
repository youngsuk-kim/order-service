package me.bread.order.domain.common

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe

class PhoneNumberTest : FeatureSpec(
    {
        feature("핸드폰 번호 유효성 검사") {
            scenario("toString() 호출 시 하이픈이 붙는다") {
                // Given When
                val sut = PhoneNumber("01053092392").toString()

                // Then
                sut shouldBe "010-5309-2392"
            }

            scenario("핸드폰 번호에는 하이픈이 들어가면 안된다") {
                // Given When Then
                shouldThrow<IllegalArgumentException> {
                    PhoneNumber("010-5309-2392")
                }
            }

            scenario("핸드폰 번호 등록 성공") {
                // Given When
                val sut = PhoneNumber("01053092392")

                // Then
                sut shouldBe PhoneNumber("01053092392")
            }
        }
    },
)
