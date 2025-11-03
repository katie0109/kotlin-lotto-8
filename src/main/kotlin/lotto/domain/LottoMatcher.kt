package lotto.domain

import lotto.Lotto

object LottoMatcher {

    fun countMatches(lotto: Lotto, winningNumbers: WinningNumbers): Int {
        return lotto.getNumbers().count { it in winningNumbers.getNumbers() }
    }

    fun matchesBonus(lotto: Lotto, bonusNumber: BonusNumber): Boolean {
        return bonusNumber.value in lotto.getNumbers()
    }
}