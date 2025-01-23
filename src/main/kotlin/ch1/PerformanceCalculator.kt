package ch1

open class PerformanceCalculator(val performance: Performance, var play: Play) {
    open fun amount(): Int {
        var result: Int

        when (this.play.type) {
            "tragedy" -> throw Error("오류 발생")

            "comedy" -> { // 희극
                result = 30000
                if (this.performance.audience > 20) {
                    result += 10000 + 500 * (this.performance.audience - 20)
                }
                result += 300 * this.performance.audience
            }

            else -> {
                throw IllegalArgumentException("알 수 없는 장르: ${this.play.type}")
            }
        }
        return result
    }

    fun volumeCredits(): Int {
        var result = 0
        result += maxOf(this.performance.audience - 30, 0)
        if ("comedy" == this.play.type)
            result += this.performance.audience / 5

        return result
    }
}

class TragedyCalculator(performance: Performance, play: Play): PerformanceCalculator(performance, play) {
    override fun amount(): Int {
        var result = 40000
        if (this.performance.audience > 30) {
            result += 1000 * (this.performance.audience - 30)
        }
        return result
    }
}

class ComedyCalculator(performance: Performance, play: Play): PerformanceCalculator(performance, play) {}
