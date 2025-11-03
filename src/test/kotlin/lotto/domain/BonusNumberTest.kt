package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 30, 45])
    fun `1부터 45 사이의 숫자로 보너스 번호를 생성할 수 있다`(number: Int) {
        val bonusNumber = BonusNumber(number)

        assertThat(bonusNumber.value).isEqualTo(number)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, 46, 100])
    fun `보너스 번호가 1부터 45 범위를 벗어나면 예외가 발생한다`(number: Int) {
        assertThatThrownBy { BonusNumber(number) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }
}