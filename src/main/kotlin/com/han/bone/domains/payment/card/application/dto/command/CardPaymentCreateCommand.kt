package com.han.bone.domains.payment.card.application.dto.command

data class CardPaymentCreateCommand(
    val orderId: String,
    val cardNumber: String,
    val amount: Long,
) {

}
