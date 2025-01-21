package ch1

import java.text.NumberFormat
import java.util.*

data class StatementData(
    val customer: String,
    val performances: List<EnrichedPerformance>,
    var totalAmount: Int = 0,
    var totalVolumeCredits: Int = 0
)

data class EnrichedPerformance(
    val playID: String,
    val audience: Int,
    val play: Play,

    // TODO: 2025.01.20 lateinit primitive type
    var amount: Int = 0,
    var volumeCredits: Int = 0
)

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    fun playFor(performance: Performance) = plays[performance.playID]!!

    fun amountFor(performance: EnrichedPerformance): Int {
        var result: Int

        when (performance.play.type) {
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
                throw IllegalArgumentException("알 수 없는 장르: ${performance.play.type}")
            }
        }
        return result
    }

    fun volumeCreditsFor(performance: EnrichedPerformance): Int {
        var result = 0
        result += maxOf(performance.audience - 30, 0)
        if ("comedy" == performance.play.type) result += performance.audience / 5

        return result
    }

    fun totalAmount(data: StatementData): Int {
        return data.performances.fold(0) { total, p -> total + p.amount }
    }

    fun totalVolumeCredits(data: StatementData): Int {
        return data.performances.fold(0) { total, p -> total + p.volumeCredits }
    }

    fun enrichPerformance(performance: Performance): EnrichedPerformance {
        // TODO: 2025.01.20
        return EnrichedPerformance(performance.playID, performance.audience, playFor(performance)).apply {
            amount = amountFor(this)
            volumeCredits = volumeCreditsFor(this)
        }
    }

    // TODO: 2025.01.20 apply? this?
    val statementData = StatementData(invoice.customer, invoice.performances.map { enrichPerformance(it) }).apply {
        totalAmount = totalAmount(this)
        totalVolumeCredits = totalVolumeCredits(this)
    }
    return renderPlainText(statementData, plays)
}

fun renderPlainText(data: StatementData, plays: Map<String, Play>): String {

    fun usd(number: Int): String {
        return NumberFormat.getCurrencyInstance(Locale.US).apply { minimumFractionDigits = 2 }.format(number / 100)
    }

    var result = "청구 내역 (고객명: ${data.customer})\n"

    data.performances.forEach { perf ->
        result += " ${perf.play.name}: ${usd(perf.amount)} (${perf.audience}석)\n"
    }

    result += "총액: ${usd(data.totalAmount)}\n"
    result += "적립 포인트: ${data.totalVolumeCredits}점\n"
    return result
}
