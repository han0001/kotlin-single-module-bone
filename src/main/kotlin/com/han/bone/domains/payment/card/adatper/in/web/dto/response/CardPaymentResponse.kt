package com.han.bone.domains.payment.card.adatper.`in`.web.dto.response

import com.han.bone.domains.payment.card.domain.CardPayment


data class CardPaymentResponse(
    val orderId: String,
    val amount: Long,
) {

    companion object {
        fun of(cardPayment: CardPayment) = CardPaymentResponse(
            orderId = cardPayment.orderId,
            amount = cardPayment.amount,
        )
    }

}
