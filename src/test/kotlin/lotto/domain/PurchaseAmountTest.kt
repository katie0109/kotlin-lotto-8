package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {

    @ParameterizedTest
    @ValueSource(ints = [1000, 5000, 10000])
    fun `숫자 형식의 구매 금액을 생성할 수 있다`(amount: Int) {
        val purchaseAmount = PurchaseAmount(amount)

        assertThat(purchaseAmount.value).isEqualTo(amount)
    }
}