package lotto.domain

import lotto.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoStoreTest {

    @Test
    fun `구매한 로또 목록을 저장할 수 있다`() {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(7, 8, 9, 10, 11, 12))
        )

        val lottoStore = LottoStore(lottos)

        assertThat(lottoStore.getLottos()).hasSize(2)
        assertThat(lottoStore.getLottos()).isEqualTo(lottos)
    }

    @Test
    fun `빈 로또 목록을 저장할 수 있다`() {
        val lottoStore = LottoStore(emptyList())

        assertThat(lottoStore.getLottos()).isEmpty()
    }
}