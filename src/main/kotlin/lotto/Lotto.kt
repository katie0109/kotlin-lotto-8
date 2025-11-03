package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == LOTTO_NUMBER_COUNT) { "[ERROR] 로또 번호는 6개여야 합니다." }
        require(numbers.distinct().size == LOTTO_NUMBER_COUNT) {
            "[ERROR] 로또 번호는 중복될 수 없습니다."
        }
    }


    // TODO: 추가 기능 구현
    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
    }
}
