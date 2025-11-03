package lotto.domain

@JvmInline
value class PurchaseAmount(val value: Int) {

    init {
        require(value % LOTTO_PRICE == 0) {
            "[ERROR] 구매 금액은 ${LOTTO_PRICE}원 단위여야 합니다."
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}