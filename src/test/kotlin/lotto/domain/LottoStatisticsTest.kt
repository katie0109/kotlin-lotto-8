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

    @Test
    fun `총 당첨 금액을 계산할 수 있다`() {
        val ranks = listOf(Rank.FIFTH, Rank.FOURTH, Rank.FIFTH, null)
        // 5등(5,000) * 2 + 4등(50,000) * 1 = 60,000

        val statistics = LottoStatistics(ranks)

        assertThat(statistics.getTotalPrizeMoney()).isEqualTo(60_000)
    }

    @Test
    fun `당첨되지 않으면 총 당첨 금액은 0이다`() {
        val ranks = listOf(null, null, null)

        val statistics = LottoStatistics(ranks)

        assertThat(statistics.getTotalPrizeMoney()).isEqualTo(0)
    }

    @Test
    fun `1등에 당첨되면 총 당첨 금액은 20억이다`() {
        val ranks = listOf(Rank.FIRST)

        val statistics = LottoStatistics(ranks)

        assertThat(statistics.getTotalPrizeMoney()).isEqualTo(2_000_000_000)
    }

    @Test
    fun `수익률을 계산할 수 있다`() {
        val ranks = listOf(Rank.FIFTH) // 5,000원
        val purchaseAmount = PurchaseAmount(8000) // 8,000원 투자

        val statistics = LottoStatistics(ranks)
        val profitRate = statistics.calculateProfitRate(purchaseAmount)

        assertThat(profitRate).isEqualTo(62.5) // 5,000 / 8,000 * 100 = 62.5%
    }

    @Test
    fun `당첨 금액이 구매 금액과 같으면 수익률은 100퍼센트이다`() {
        val ranks = listOf(Rank.FIFTH, Rank.FIFTH) // 10,000원
        val purchaseAmount = PurchaseAmount(10000)

        val statistics = LottoStatistics(ranks)
        val profitRate = statistics.calculateProfitRate(purchaseAmount)

        assertThat(profitRate).isEqualTo(100.0)
    }

    @Test
    fun `당첨되지 않으면 수익률은 0이다`() {
        val ranks = listOf(null, null)
        val purchaseAmount = PurchaseAmount(2000)

        val statistics = LottoStatistics(ranks)
        val profitRate = statistics.calculateProfitRate(purchaseAmount)

        assertThat(profitRate).isEqualTo(0.0)
    }

    @Test
    fun `수익률은 소수점 둘째 자리에서 반올림한다`() {
        val ranks = listOf(Rank.FIFTH) // 5,000원
        val purchaseAmount = PurchaseAmount(14000) // 14,000원 투자

        val statistics = LottoStatistics(ranks)
        val profitRate = statistics.calculateProfitRate(purchaseAmount)

        // 5,000 / 14,000 * 100 = 35.714285... -> 35.7
        assertThat(profitRate).isEqualTo(35.7)
    }
}