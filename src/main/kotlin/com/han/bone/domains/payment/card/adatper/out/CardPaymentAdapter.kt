package com.han.bone.domains.payment.card.adatper.out

import com.han.bone.domains.payment.card.adatper.out.model.CardPaymentEntity
import com.han.bone.domains.payment.card.adatper.out.repositoty.CardPaymentRepository
import com.han.bone.domains.payment.card.application.port.out.CardPaymentPort
import com.han.bone.domains.payment.card.domain.CardPayment
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CardPaymentAdapter(
    val cardPaymentRepository: CardPaymentRepository,
) : CardPaymentPort {

    override fun findByOrderId(orderId: String): CardPayment? {
        return cardPaymentRepository.findByOrderId(orderId)?.toDomain()
    }

    override fun save(cardPayment: CardPayment): CardPayment {
        return run {
            cardPaymentRepository.findByOrderId(cardPayment.orderId)
                ?. apply(cardPayment)
                ?: CardPaymentEntity.fromDomain(cardPayment)
        }.let(cardPaymentRepository::saveAndFlush).toDomain()
    }

}