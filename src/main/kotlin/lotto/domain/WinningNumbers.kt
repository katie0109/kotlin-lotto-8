package lotto.domain

class WinningNumbers private constructor(private val numbers: List<Int>) {

    init {
        require(numbers.size == WINNING_NUMBER_COUNT) {
            "[ERROR] 당첨 번호는 ${WINNING_NUMBER_COUNT}개여야 합니다."
        }
    }

    fun getNumbers(): List<Int> {
        return numbers
    }

    companion object {
        private const val WINNING_NUMBER_COUNT = 6

        fun from(input: String): WinningNumbers {
            val numbers = input.split(",")
                .map { it.trim().toInt() }
            return WinningNumbers(numbers)
        }
    }
}