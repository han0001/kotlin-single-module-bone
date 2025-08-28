package com.han.bone.domains.payment.card.application.service

import com.han.bone.domains.payment.card.adatper.`in`.web.dto.response.CardPaymentCreateResponse
import com.han.bone.domains.payment.card.adatper.`in`.web.dto.response.CardPaymentResponse
import com.han.bone.domains.payment.card.application.dto.command.CardPaymentCreateCommand
import com.han.bone.domains.payment.card.application.port.`in`.CardPaymentUseCase
import com.han.bone.domains.payment.card.application.port.out.CardPaymentPort
import com.han.bone.domains.payment.card.domain.CardPayment
import org.springframework.stereotype.Service

@Service
class CardPaymentApplicationService(
    private val cardPaymentPort: CardPaymentPort
) : CardPaymentUseCase {

    override fun findByOrderId(orderId: String): CardPaymentResponse {
        val cardPayment = cardPaymentPort.findByOrderId(orderId) ?: throw NoSuchElementException("Card payment not found")
        return CardPaymentResponse.of(cardPayment)
    }

    override fun create(command: CardPaymentCreateCommand): CardPaymentCreateResponse {
        val cardPayment = cardPaymentPort.findByOrderId(command.orderId)?.let {
            throw IllegalArgumentException("Duplicate orderId=${command.orderId}")
        }

        val payment = CardPayment.create(command)
        val savedPayment = cardPaymentPort.save(payment)

        return CardPaymentCreateResponse.of(savedPayment)
    }


}