package lotto.domain

import camp.nextstep.edu.missionutils.Randoms
import lotto.Lotto

object LottoMachine {

    fun generate(): Lotto {
        val numbers = Randoms.pickUniqueNumbersInRange(
            MIN_LOTTO_NUMBER,
            MAX_LOTTO_NUMBER,
            LOTTO_NUMBER_COUNT
        )
        return Lotto(numbers)
    }

    fun generateLottos(count: Int): List<Lotto> {
        return List(count) { generate() }
    }

    private const val MIN_LOTTO_NUMBER = 1
    private const val MAX_LOTTO_NUMBER = 45
    private const val LOTTO_NUMBER_COUNT = 6
}