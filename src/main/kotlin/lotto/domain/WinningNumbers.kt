package lotto.domain

class WinningNumbers private constructor(private val numbers: List<Int>) {

    init {
        require(numbers.size == WINNING_NUMBER_COUNT) {
            "[ERROR] 당첨 번호는 ${WINNING_NUMBER_COUNT}개여야 합니다."
        }
        validateNumberRange(numbers)
        validateDuplication(numbers)
    }

    fun getNumbers(): List<Int> {
        return numbers
    }

    companion object {
        private const val WINNING_NUMBER_COUNT = 6
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45

        fun from(input: String): WinningNumbers {
            val numbers = parseNumbers(input)
            return WinningNumbers(numbers)
        }

        private fun parseNumbers(input: String): List<Int> {
            return try {
                input.split(",")
                    .map { it.trim().toInt() }
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")
            }
        }

        private fun validateNumberRange(numbers: List<Int>) {
            require(numbers.all { it in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER }) {
                "[ERROR] 당첨 번호는 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER} 사이의 숫자여야 합니다."
            }
        }

        private fun validateDuplication(numbers: List<Int>) {
            require(numbers.distinct().size == numbers.size) {
                "[ERROR] 당첨 번호는 중복될 수 없습니다."
            }
        }
    }
}