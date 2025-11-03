package lotto.view

import lotto.Lotto
import lotto.domain.LottoStatistics
import lotto.domain.Rank
import java.text.DecimalFormat

object OutputView {

    private val moneyFormat = DecimalFormat("#,###")

    fun printLottoCount(count: Int) {
        println()
        println("${count}개를 구매했습니다.")
    }

    fun printLotto(lotto: Lotto) {
        println(lotto.getNumbers())
    }

    fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { printLotto(it) }
    }

    fun printStatisticsHeader() {
        println()
        println("당첨 통계")
        println("---")
    }

    fun printStatistics(statistics: LottoStatistics) {
        printRankStatistics(Rank.FIFTH, statistics)
        printRankStatistics(Rank.FOURTH, statistics)
        printRankStatistics(Rank.THIRD, statistics)
        printSecondRankStatistics(statistics)
        printRankStatistics(Rank.FIRST, statistics)
    }

    private fun printRankStatistics(rank: Rank, statistics: LottoStatistics) {
        val count = statistics.getCount(rank)
        val prizeMoney = moneyFormat.format(rank.prizeMoney)
        println("${rank.matchCount}개 일치 (${prizeMoney}원) - ${count}개")
    }

    private fun printSecondRankStatistics(statistics: LottoStatistics) {
        val count = statistics.getCount(Rank.SECOND)
        val prizeMoney = moneyFormat.format(Rank.SECOND.prizeMoney)
        println("5개 일치, 보너스 볼 일치 (${prizeMoney}원) - ${count}개")
    }
}