package com.han.bone.domains.payment.card.domain

import com.han.bone.domains.payment.card.application.dto.command.CardPaymentCreateCommand


class CardPayment (
    val id: Long = 0,
    val orderId: String,
    val cardNumber: String,
    var amount: Long,
) {

    fun changeAmount(newAmount: Long) {
        amount = newAmount
    }

    companion object {
        fun create(command: CardPaymentCreateCommand): CardPayment {
            val payment = CardPayment(
                orderId = command.orderId,
                cardNumber = command.cardNumber,
                amount = command.amount,
            )

            return payment
        }
    }


}