package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(ints = [1000, 5000, 10000])
    fun `숫자 형식의 구매 금액을 생성할 수 있다`(amount: Int) {
        val purchaseAmount = PurchaseAmount(amount)

        assertThat(purchaseAmount.value).isEqualTo(amount)
    }

    @ParameterizedTest
    @ValueSource(ints = [500, 1500, 2300, 10001])
    fun `구매 금액이 1000원 단위가 아니면 예외가 발생한다`(amount: Int) {
        assertThatThrownBy { PurchaseAmount(amount) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1000])
    fun `구매 금액이 1000원 미만이면 예외가 발생한다`(amount: Int) {
        assertThatThrownBy { PurchaseAmount(amount) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }
}