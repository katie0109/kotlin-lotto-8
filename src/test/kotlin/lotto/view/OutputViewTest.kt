package lotto.view

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
}