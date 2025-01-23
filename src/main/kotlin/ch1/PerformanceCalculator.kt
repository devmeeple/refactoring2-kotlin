package ch1

open class PerformanceCalculator(val performance: Performance, var play: Play) {
    open fun amount(): Int {
        // TODO: 2025.01.23 
        throw NotImplementedError("서브클래스에서 처리하도록 설계되었습니다.")
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

class ComedyCalculator(performance: Performance, play: Play): PerformanceCalculator(performance, play) {
    override fun amount(): Int {
        var result = 30000
        if (this.performance.audience > 20) {
            result += 10000 + 500 * (this.performance.audience - 20)
        }
        result += 300 * this.performance.audience
        return result
    }
}
