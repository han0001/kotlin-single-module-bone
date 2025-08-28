package com.han.bone.domains.payment.card.application.port.out

import com.han.bone.domains.payment.card.domain.CardPayment


interface CardPaymentPort {

    fun findByOrderId(orderId: String): CardPayment?

    fun save(cardPayment: CardPayment): CardPayment
}