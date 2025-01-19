package ch1

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class StatementTest : BehaviorSpec({
    Given("청구서(invoice), 연극 정보(plays)") {
        val invoice = invoices[0]
        val plays = plays

        When("statement 함수를 실행하면") {
            val result = statement(invoice, plays)

            Then("공연료 청구서를 반환한다") {
                result shouldBe """
                    청구 내역 (고객명: BigCo)
                     Hamlet: $650.00 (55석)
                     As You Like It: $580.00 (35석)
                     Othello: $500.00 (40석)
                    총액: $1,730.00
                    적립 포인트: 47점
                    
                """.trimIndent()
            }
        }
    }
})
