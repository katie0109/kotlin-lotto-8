package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
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

    @Test
    fun `보너스 번호가 당첨 번호와 중복되지 않으면 생성할 수 있다`() {
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")
        val bonusNumber = BonusNumber.of(7, winningNumbers)

        assertThat(bonusNumber.value).isEqualTo(7)
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외가 발생한다`() {
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")

        assertThatThrownBy { BonusNumber.of(6, winningNumbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")

        assertThatThrownBy { BonusNumber.of(1, winningNumbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }
}