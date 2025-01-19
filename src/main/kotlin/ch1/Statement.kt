package ch1

import java.text.NumberFormat
import java.util.*

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    var totalAmount = 0
    var volumeCredits = 0
    var result = "청구 내역 (고객명: ${invoice.customer})\n"
    val format = NumberFormat.getCurrencyInstance(Locale.US).apply { minimumFractionDigits = 2 }

    invoice.performances.forEach { perf ->
        val play = plays[perf.playID]!!
        val thisAmount = amountFor(play, perf)

        // 포인트 적립
        volumeCredits += maxOf(perf.audience - 30, 0)
        // 희극 관객 5명 마다 추가 포인트 제공
        if ("comedy" == play.type) volumeCredits += perf.audience / 5

        // 청구 내역 출력
        result += " ${play.name}: ${format.format(thisAmount / 100)} (${perf.audience}석)\n"
        totalAmount += thisAmount
    }
    result += "총액: ${format.format(totalAmount / 100)}\n"
    result += "적립 포인트: ${volumeCredits}점\n"
    return result
}

private fun amountFor(play: Play, performance: Performance): Int {
    var result: Int

    when (play.type) {
        "tragedy" -> { // 비극
            result = 40000
            if (performance.audience > 30) {
                result += 1000 * (performance.audience - 30)
            }
        }

        "comedy" -> { // 희극
            result = 30000
            if (performance.audience > 20) {
                result += 10000 + 500 * (performance.audience - 20)
            }
            result += 300 * performance.audience
        }

        else -> {
            throw IllegalArgumentException("알 수 없는 장르: ${play.type}")
        }
    }
    return result
}
