package lotto.domain

@JvmInline
value class PurchaseAmount(val value: Int) {

    init {
        require(value >= LOTTO_PRICE) {
            "[ERROR] 구매 금액은 ${LOTTO_PRICE}원 이상이어야 합니다."
        }
        require(value % LOTTO_PRICE == 0) {
            "[ERROR] 구매 금액은 ${LOTTO_PRICE}원 단위여야 합니다."
        }
    }

    fun getLottoCount(): Int {
        return value / LOTTO_PRICE
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}