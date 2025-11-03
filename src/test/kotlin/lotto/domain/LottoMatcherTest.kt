package lotto.domain

import lotto.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMatcherTest {

    @Test
    fun `로또 번호와 당첨 번호의 일치 개수를 계산할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = WinningNumbers.from("1,2,3,7,8,9")

        val matchCount = LottoMatcher.countMatches(lotto, winningNumbers)

        assertThat(matchCount).isEqualTo(3)
    }

    @Test
    fun `모든 번호가 일치하면 6개를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")

        val matchCount = LottoMatcher.countMatches(lotto, winningNumbers)

        assertThat(matchCount).isEqualTo(6)
    }

    @Test
    fun `일치하는 번호가 없으면 0개를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = WinningNumbers.from("10,20,30,40,41,42")

        val matchCount = LottoMatcher.countMatches(lotto, winningNumbers)

        assertThat(matchCount).isEqualTo(0)
    }

    @Test
    fun `로또 번호에 보너스 번호가 포함되어 있으면 true를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val bonusNumber = BonusNumber(7)

        val hasBonus = LottoMatcher.matchesBonus(lotto, bonusNumber)

        assertThat(hasBonus).isTrue()
    }

    @Test
    fun `로또 번호에 보너스 번호가 포함되어 있지 않으면 false를 반환한다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = BonusNumber(7)

        val hasBonus = LottoMatcher.matchesBonus(lotto, bonusNumber)

        assertThat(hasBonus).isFalse()
    }

    @Test
    fun `6개 일치하면 1등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber(7)

        val rank = LottoMatcher.match(lotto, winningNumbers, bonusNumber)

        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개 일치하고 보너스 번호가 일치하면 2등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber(7)

        val rank = LottoMatcher.match(lotto, winningNumbers, bonusNumber)

        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개 일치하고 보너스 번호가 일치하지 않으면 3등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 8))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber(7)

        val rank = LottoMatcher.match(lotto, winningNumbers, bonusNumber)

        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개 일치하면 4등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 8, 9))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber(7)

        val rank = LottoMatcher.match(lotto, winningNumbers, bonusNumber)

        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개 일치하면 5등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 8, 9, 10))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber(7)

        val rank = LottoMatcher.match(lotto, winningNumbers, bonusNumber)

        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `3개 미만 일치하면 당첨되지 않는다`() {
        val lotto = Lotto(listOf(1, 2, 8, 9, 10, 11))
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber(7)

        val rank = LottoMatcher.match(lotto, winningNumbers, bonusNumber)

        assertThat(rank).isNull()
    }
}