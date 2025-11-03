package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.domain.PurchaseAmount
import lotto.domain.WinningNumbers

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

    fun readWinningNumbers(): String {
        println()
        println("당첨 번호를 입력해 주세요.")
        return Console.readLine()
    }

    fun readValidWinningNumbers(): WinningNumbers {
        while (true) {
            try {
                val input = readWinningNumbers()
                return WinningNumbers.from(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun readBonusNumber(): String {
        println()
        println("보너스 번호를 입력해 주세요.")
        return Console.readLine()
    }
}