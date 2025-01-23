package ch1

fun createStatementData(invoice: Invoice, plays: Map<String, Play>): StatementData {
    fun playFor(performance: Performance) = plays[performance.playID]!!

    fun enrichPerformance(performance: Performance): EnrichedPerformance {
        val calculator = PerformanceCalculator(performance, playFor(performance))

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
