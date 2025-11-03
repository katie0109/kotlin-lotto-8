package lotto.domain

@JvmInline
value class BonusNumber(val value: Int) {

    init {
        require(value in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
            "[ERROR] 보너스 번호는 $MIN_LOTTO_NUMBER 부터 $MAX_LOTTO_NUMBER 사이의 숫자여야 합니다."
        }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
    }
}