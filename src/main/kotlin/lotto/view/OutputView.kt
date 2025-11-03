package lotto.view

import lotto.Lotto

object OutputView {

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
}