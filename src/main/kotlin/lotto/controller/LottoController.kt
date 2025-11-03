package lotto.controller

import lotto.domain.LottoMachine
import lotto.domain.LottoStore
import lotto.view.InputView
import lotto.view.OutputView

class LottoController {

    fun run() {
        val purchaseAmount = InputView.readValidPurchaseAmount()
        val lottoStore = purchaseLottos(purchaseAmount)
        OutputView.printLottoCount(lottoStore.getCount())
        OutputView.printLottos(lottoStore.getLottos())
    }

    private fun purchaseLottos(purchaseAmount: lotto.domain.PurchaseAmount): LottoStore {
        val count = purchaseAmount.getLottoCount()
        val lottos = LottoMachine.generateLottos(count)
        return LottoStore(lottos)
    }
}