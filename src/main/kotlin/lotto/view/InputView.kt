package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.domain.PurchaseAmount

object InputView {

    fun readPurchaseAmount(): String {
        println("구입금액을 입력해 주세요.")
        return Console.readLine()
    }

    fun readValidPurchaseAmount(): PurchaseAmount {
        while (true) {
            try {
                val input = readPurchaseAmount()
                val amount = input.toInt()
                return PurchaseAmount(amount)
            } catch (e: NumberFormatException) {
                println("[ERROR] 구매 금액은 숫자여야 합니다.")
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}