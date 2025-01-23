package ch1

fun createStatementData(invoice: Invoice, plays: Map<String, Play>): StatementData {
    fun enrichPerformance(performance: Performance): EnrichedPerformance {
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

        return EnrichedPerformance(performance.playID, performance.audience, playFor(performance)).apply {
            amount = amountFor(this)
            volumeCredits = volumeCreditsFor(this)
        }
    }

    fun totalAmount(data: StatementData): Int {
        return data.performances.fold(0) { total, p -> total + p.amount }
    }

    fun totalVolumeCredits(data: StatementData): Int {
        return data.performances.fold(0) { total, p -> total + p.volumeCredits }
    }

    return StatementData(invoice.customer, invoice.performances.map { enrichPerformance(it) }).apply {
        totalAmount = totalAmount(this)
        totalVolumeCredits = totalVolumeCredits(this)
    }
}
