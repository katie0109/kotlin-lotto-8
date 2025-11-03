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
}