package lotto.view

import lotto.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OutputViewTest {

    private val outputStream = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }

    @Test
    fun `로또 개수를 출력할 수 있다`() {
        OutputView.printLottoCount(3)

        assertThat(outputStream.toString()).contains("3개를 구매했습니다.")
    }

    @Test
    fun `1개를 구매했을 때 출력할 수 있다`() {
        OutputView.printLottoCount(1)

        assertThat(outputStream.toString()).contains("1개를 구매했습니다.")
    }

    @Test
    fun `로또 번호를 출력할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

        OutputView.printLotto(lotto)

        assertThat(outputStream.toString()).contains("[1, 2, 3, 4, 5, 6]")
    }

    @Test
    fun `여러 로또를 출력할 수 있다`() {
        val lottos = listOf(
            Lotto(listOf(8, 21, 23, 41, 42, 43)),
            Lotto(listOf(3, 5, 11, 16, 32, 38))
        )

        OutputView.printLottos(lottos)

        val output = outputStream.toString()
        assertThat(output).contains("[8, 21, 23, 41, 42, 43]")
        assertThat(output).contains("[3, 5, 11, 16, 32, 38]")
    }

    @Test
    fun `당첨 통계 헤더를 출력할 수 있다`() {
        OutputView.printStatisticsHeader()

        val output = outputStream.toString()
        assertThat(output).contains("당첨 통계")
        assertThat(output).contains("---")
    }
}