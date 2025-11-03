package lotto.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 30, 45])
    fun `1부터 45 사이의 숫자로 로또 번호를 생성할 수 있다`(number: Int) {
        val lottoNumber = LottoNumber(number)
        assertEquals(number, lottoNumber.value)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, 46, 100])
    fun `로또 번호가 1부터 45 범위를 벗어나면 예외가 발생한다`(number: Int) {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            LottoNumber(number)
        }
        assertTrue(exception.message!!.contains("[ERROR]"))
    }
}