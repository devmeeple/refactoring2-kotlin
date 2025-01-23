package ch1

fun createStatementData(invoice: Invoice, plays: Map<String, Play>): StatementData {
    fun playFor(performance: Performance) = plays[performance.playID]!!

    fun createPerformanceCalculator(performance: Performance, play: Play): PerformanceCalculator {
        return when(play.type) {
            "tragedy" -> TragedyCalculator(performance, play)
            "comedy" -> ComedyCalculator(performance, play)
            else -> throw IllegalArgumentException("알 수 없는 장르: ${play.type}")
        }
    }

    fun enrichPerformance(performance: Performance): EnrichedPerformance {
        val calculator = createPerformanceCalculator(performance, playFor(performance))

        return EnrichedPerformance(performance.playID, performance.audience, calculator.play).apply {
            amount = calculator.amount()
            volumeCredits = calculator.volumeCredits()
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
