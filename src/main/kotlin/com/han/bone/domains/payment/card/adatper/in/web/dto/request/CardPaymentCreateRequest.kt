package com.han.bone.domains.payment.card.adatper.`in`.web.dto.request

import com.han.bone.domains.payment.card.application.dto.command.CardPaymentCreateCommand


data class CardPaymentCreateRequest(
    val orderId: String,
    val cardNumber: String,
    val amount: Long,
) {

    fun toCommand() = CardPaymentCreateCommand(
        orderId = orderId,
        cardNumber = cardNumber,
        amount = amount,
    )

}
