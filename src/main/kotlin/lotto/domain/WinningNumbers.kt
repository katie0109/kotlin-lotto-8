package lotto.domain

class WinningNumbers private constructor(private val numbers: List<Int>) {

    fun getNumbers(): List<Int> {
        return numbers
    }

    companion object {
        fun from(input: String): WinningNumbers {
            val numbers = input.split(",")
                .map { it.trim().toInt() }
            return WinningNumbers(numbers)
        }
    }
}