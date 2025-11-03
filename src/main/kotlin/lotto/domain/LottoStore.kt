package lotto.domain

import lotto.Lotto

class LottoStore(private val lottos: List<Lotto>) {

    fun getLottos(): List<Lotto> {
        return lottos
    }

    fun getCount(): Int {
        return lottos.size
    }
}