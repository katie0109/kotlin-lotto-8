package lotto.domain

class LottoStatistics(ranks: List<Rank?>) {

    private val rankCounts: Map<Rank, Int> = ranks
        .filterNotNull()
        .groupingBy { it }
        .eachCount()

    fun getCount(rank: Rank): Int {
        return rankCounts[rank] ?: 0
    }
}