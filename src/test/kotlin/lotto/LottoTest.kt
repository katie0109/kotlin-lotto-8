package lotto

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue

class LottoTest {
//    @Test
//    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
//        assertThrows<IllegalArgumentException> {
//            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
//        }
//    }
//
//    // TODO: 테스트가 통과하도록 프로덕션 코드 구현
//    @Test
//    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
//        assertThrows<IllegalArgumentException> {
//            Lotto(listOf(1, 2, 3, 4, 5, 5))
//        }
//    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @Test
    fun `6개의 중복되지 않는 숫자로 로또를 생성할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))

        assertThat(lotto).isNotNull
    }
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
        assertTrue(exception.message!!.contains("[ERROR]"))
    }

    @Test
    fun `로또 번호의 개수가 6개 미만이면 예외가 발생한다`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Lotto(listOf(1, 2, 3, 4, 5))
        }
        assertTrue(exception.message!!.contains("[ERROR]"))
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
        assertTrue(exception.message!!.contains("[ERROR]"))
    }

    @Test
    fun `로또 번호는 오름차순으로 정렬되어 반환된다`() {
        val lotto = Lotto(listOf(45, 8, 23, 1, 14, 32))

        assertThat(lotto.getNumbers()).containsExactly(1, 8, 14, 23, 32, 45)
    }
}
