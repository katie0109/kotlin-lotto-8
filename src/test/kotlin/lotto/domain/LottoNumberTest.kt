package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 30, 45])
    fun `1부터 45 사이의 숫자로 로또 번호를 생성할 수 있다`(number: Int) {
        val lottoNumber = LottoNumber(number)

        assertThat(lottoNumber.value).isEqualTo(number)
    }
}