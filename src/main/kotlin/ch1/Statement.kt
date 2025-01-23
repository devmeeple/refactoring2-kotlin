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

    var amount: Int = 0,
    var volumeCredits: Int = 0
)

fun statement(invoice: Invoice, plays: Map<String, Play>):String  {
    return renderPlainText(createStatementData(invoice, plays), plays)
}

fun renderPlainText(data: StatementData, plays: Map<String, Play>): String {
    var result = "청구 내역 (고객명: ${data.customer})\n"

    data.performances.forEach { perf ->
        result += "  ${perf.play.name}: ${usd(perf.amount)} (${perf.audience}석)\n"
    }

    result += "총액: ${usd(data.totalAmount)}\n"
    result += "적립 포인트: ${data.totalVolumeCredits}점\n"
    return result
}

fun htmlStatement(invoice: Invoice, plays: Map<String, Play>): String {
    return renderHtml(createStatementData(invoice, plays))
}

fun renderHtml(data: StatementData): String {
    var result = "<h1>청구 내역 (고객명: ${data.customer})</h1>\n"
    result += "<table>\n"
    result += "<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>"

    data.performances.forEach { perf ->
        result += "  <tr><td>${perf.play.name}</td><td>(${perf.audience}석)</td>"
        result += "<td>${usd(perf.amount)}</td></tr>\n"
    }

    result += "</table>\n"
    result += "<p>총액: <em>${usd(data.totalAmount)}</em></p>\n"
    result += "<p>적립 포인트: <em>${data.totalVolumeCredits}</em>점</p>\n"

    return result;
}

fun usd(number: Int): String {
    return NumberFormat.getCurrencyInstance(Locale.US).apply { minimumFractionDigits = 2 }.format(number / 100)
}

