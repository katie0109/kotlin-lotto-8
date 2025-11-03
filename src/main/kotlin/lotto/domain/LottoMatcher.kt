package lotto.domain

import lotto.Lotto

object LottoMatcher {

    fun countMatches(lotto: Lotto, winningNumbers: WinningNumbers): Int {
        return lotto.getNumbers().count { it in winningNumbers.getNumbers() }
    }

    fun matchesBonus(lotto: Lotto, bonusNumber: BonusNumber): Boolean {
        return bonusNumber.value in lotto.getNumbers()
    }

    fun match(lotto: Lotto, winningNumbers: WinningNumbers, bonusNumber: BonusNumber): Rank? {
        val matchCount = countMatches(lotto, winningNumbers)
        val bonusMatch = matchesBonus(lotto, bonusNumber)
        return Rank.of(matchCount, bonusMatch)
    }
}