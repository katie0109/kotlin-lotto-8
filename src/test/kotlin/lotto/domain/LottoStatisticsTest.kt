package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStatisticsTest {

    @Test
    fun `등수별 당첨 개수를 집계할 수 있다`() {
        val ranks = listOf(Rank.FIFTH, Rank.FOURTH, Rank.FIFTH, null, Rank.THIRD)

        val statistics = LottoStatistics(ranks)

        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(2)
        assertThat(statistics.getCount(Rank.FOURTH)).isEqualTo(1)
        assertThat(statistics.getCount(Rank.THIRD)).isEqualTo(1)
        assertThat(statistics.getCount(Rank.SECOND)).isEqualTo(0)
        assertThat(statistics.getCount(Rank.FIRST)).isEqualTo(0)
    }

    @Test
    fun `모두 당첨되지 않으면 모든 등수의 개수가 0이다`() {
        val ranks = listOf(null, null, null)

        val statistics = LottoStatistics(ranks)

        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(0)
        assertThat(statistics.getCount(Rank.FOURTH)).isEqualTo(0)
        assertThat(statistics.getCount(Rank.THIRD)).isEqualTo(0)
        assertThat(statistics.getCount(Rank.SECOND)).isEqualTo(0)
        assertThat(statistics.getCount(Rank.FIRST)).isEqualTo(0)
    }

    @Test
    fun `빈 리스트로 통계를 생성할 수 있다`() {
        val statistics = LottoStatistics(emptyList())

        assertThat(statistics.getCount(Rank.FIFTH)).isEqualTo(0)
    }
}