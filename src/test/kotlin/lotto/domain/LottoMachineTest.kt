package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    fun `로또를 자동으로 생성할 수 있다`() {
        val lotto = LottoMachine.generate()

        assertThat(lotto.getNumbers()).hasSize(6)
        assertThat(lotto.getNumbers()).allMatch { it in 1..45 }
        assertThat(lotto.getNumbers().distinct()).hasSize(6)
    }
}