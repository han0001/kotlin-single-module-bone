package com.han.bone.domains.payment.card.adatper.`in`.web.dto.response

import com.han.bone.domains.payment.card.domain.CardPayment


data class CardPaymentCreateResponse(
    val orderId: String,
    val cardNumber: String,
    val amount: Long,
) {
    companion object {

        fun of(cardPayment: CardPayment) = CardPaymentCreateResponse(
            orderId = cardPayment.orderId,
            cardNumber = cardPayment.cardNumber,
            amount = cardPayment.amount,
        )

    }
}
