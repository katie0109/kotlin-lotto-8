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
    }

    private fun purchaseLottos(purchaseAmount: PurchaseAmount): LottoStore {
        val count = purchaseAmount.getLottoCount()
        val lottos = LottoMachine.generateLottos(count)
        return LottoStore(lottos)
    }
}