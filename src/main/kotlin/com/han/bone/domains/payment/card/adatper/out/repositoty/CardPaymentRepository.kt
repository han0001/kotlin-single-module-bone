package com.han.bone.domains.payment.card.adatper.out.repositoty

import com.han.bone.domains.payment.card.adatper.out.model.CardPaymentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CardPaymentRepository : JpaRepository<CardPaymentEntity, Long> {

    fun findByOrderId(orderId: String): CardPaymentEntity?

}

