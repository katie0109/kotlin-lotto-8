package lotto.domain

@JvmInline
value class BonusNumber private constructor(val value: Int) {

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45

        operator fun invoke(value: Int): BonusNumber {
            require(value in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
                "[ERROR] 보너스 번호는 $MIN_LOTTO_NUMBER 부터 $MAX_LOTTO_NUMBER 사이의 숫자여야 합니다."
            }
            return BonusNumber(value)
        }

        fun of(value: Int, winningNumbers: WinningNumbers): BonusNumber {
            require(value in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
                "[ERROR] 보너스 번호는 $MIN_LOTTO_NUMBER 부터 $MAX_LOTTO_NUMBER 사이의 숫자여야 합니다."
            }
            require(value !in winningNumbers.getNumbers()) {
                "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
            }
            return BonusNumber(value)
        }
    }
}