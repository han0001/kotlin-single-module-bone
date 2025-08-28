package com.han.bone.domains.payment.card.application.port.`in`

import com.han.bone.domains.payment.card.adatper.`in`.web.dto.response.CardPaymentCreateResponse
import com.han.bone.domains.payment.card.adatper.`in`.web.dto.response.CardPaymentResponse
import com.han.bone.domains.payment.card.application.dto.command.CardPaymentCreateCommand


interface CardPaymentUseCase {

    fun findByOrderId(orderId: String): CardPaymentResponse

    fun create(command: CardPaymentCreateCommand): CardPaymentCreateResponse
}