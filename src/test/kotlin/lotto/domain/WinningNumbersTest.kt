package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class WinningNumbersTest {

    @Test
    fun `쉼표로 구분된 문자열을 파싱하여 당첨 번호를 생성할 수 있다`() {
        val winningNumbers = WinningNumbers.from("1,2,3,4,5,6")

        assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `공백이 포함된 문자열을 파싱할 수 있다`() {
        val winningNumbers = WinningNumbers.from("1, 2, 3, 4, 5, 6")

        assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `당첨 번호가 6개가 아니면 예외가 발생한다`() {
        assertThatThrownBy { WinningNumbers.from("1,2,3,4,5") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")

        assertThatThrownBy { WinningNumbers.from("1,2,3,4,5,6,7") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `당첨 번호에 숫자가 아닌 값이 포함되면 예외가 발생한다`() {
        assertThatThrownBy { WinningNumbers.from("1,2,3,a,5,6") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")

        assertThatThrownBy { WinningNumbers.from("1,2,3,4.5,5,6") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }
}