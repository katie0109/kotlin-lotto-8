package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class RankTest {

    @Test
    fun `1등은 6개 일치하고 상금은 2,000,000,000원이다`() {
        val rank = Rank.FIRST

        assertThat(rank.matchCount).isEqualTo(6)
        assertThat(rank.prizeMoney).isEqualTo(2_000_000_000)
    }

    @Test
    fun `2등은 5개 일치하고 상금은 30,000,000원이다`() {
        val rank = Rank.SECOND

        assertThat(rank.matchCount).isEqualTo(5)
        assertThat(rank.prizeMoney).isEqualTo(30_000_000)
    }

    @Test
    fun `3등은 5개 일치하고 상금은 1,500,000원이다`() {
        val rank = Rank.THIRD

        assertThat(rank.matchCount).isEqualTo(5)
        assertThat(rank.prizeMoney).isEqualTo(1_500_000)
    }

    @Test
    fun `4등은 4개 일치하고 상금은 50,000원이다`() {
        val rank = Rank.FOURTH

        assertThat(rank.matchCount).isEqualTo(4)
        assertThat(rank.prizeMoney).isEqualTo(50_000)
    }

    @Test
    fun `5등은 3개 일치하고 상금은 5,000원이다`() {
        val rank = Rank.FIFTH

        assertThat(rank.matchCount).isEqualTo(3)
        assertThat(rank.prizeMoney).isEqualTo(5_000)
    }

    @ParameterizedTest
    @CsvSource(
        "6, false, FIRST",
        "4, false, FOURTH",
        "3, false, FIFTH"
    )
    fun `일치 개수로 등수를 판정할 수 있다`(matchCount: Int, bonusMatch: Boolean, expectedRank: Rank) {
        val rank = Rank.of(matchCount, bonusMatch)

        assertThat(rank).isEqualTo(expectedRank)
    }

    @ParameterizedTest
    @CsvSource("0", "1", "2")
    fun `3개 미만 일치하면 당첨되지 않는다`(matchCount: Int) {
        val rank = Rank.of(matchCount, false)

        assertThat(rank).isNull()
    }
}