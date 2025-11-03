package lotto.domain

import kotlin.math.round

class LottoStatistics(ranks: List<Rank?>) {

    private val rankCounts: Map<Rank, Int> = ranks
        .filterNotNull()
        .groupingBy { it }
        .eachCount()

    fun getCount(rank: Rank): Int {
        return rankCounts[rank] ?: 0
    }

    fun getTotalPrizeMoney(): Int {
        return rankCounts.entries.sumOf { (rank, count) ->
            rank.prizeMoney * count
        }
    }

    fun calculateProfitRate(purchaseAmount: PurchaseAmount): Double {
        val totalPrize = getTotalPrizeMoney().toDouble()
        val totalCost = purchaseAmount.value.toDouble()
        val profitRate = (totalPrize / totalCost) * 100
        return round(profitRate * 10) / 10
    }
}