package lotto.controller

import lotto.domain.*
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {

    fun run() {
        val purchaseAmount = InputView.readValidPurchaseAmount()
        val lottoStore = purchaseLottos(purchaseAmount)
        OutputView.printLottoCount(lottoStore.getCount())
        OutputView.printLottos(lottoStore.getLottos())

        val winningNumbers = InputView.readValidWinningNumbers()
        val bonusNumber = InputView.readValidBonusNumber(winningNumbers)

        val statistics = calculateStatistics(lottoStore, winningNumbers, bonusNumber)
        printResult(statistics, purchaseAmount)
    }

    private fun purchaseLottos(purchaseAmount: PurchaseAmount): LottoStore {
        val count = purchaseAmount.getLottoCount()
        val lottos = LottoMachine.generateLottos(count)
        return LottoStore(lottos)
    }

    private fun calculateStatistics(
        lottoStore: LottoStore,
        winningNumbers: WinningNumbers,
        bonusNumber: BonusNumber
    ): LottoStatistics {
        val ranks = lottoStore.getLottos().map { lotto ->
            LottoMatcher.match(lotto, winningNumbers, bonusNumber)
        }
        return LottoStatistics(ranks)
    }

    private fun printResult(statistics: LottoStatistics, purchaseAmount: PurchaseAmount) {
        OutputView.printStatisticsHeader()
        OutputView.printStatistics(statistics)
        OutputView.printProfitRate(statistics, purchaseAmount)
    }
}