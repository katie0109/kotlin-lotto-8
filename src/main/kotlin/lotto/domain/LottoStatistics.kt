package lotto.domain

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
}