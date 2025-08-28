package com.han.bone.domains.payment.card.adatper.out.model

import com.han.bone.domains.payment.card.domain.CardPayment
import jakarta.persistence.*
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "card_payment")
class CardPaymentEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "order_id", nullable = false, unique = true)
    val orderId: String,

    @Column(name = "card_number", nullable = false)
    var cardNumber: String,

    @Column(name = "amount", nullable = false)
    var amount: Long,
) {

    fun toDomain() = CardPayment(
        id = this.id,
        orderId = this.orderId,
        cardNumber = this.cardNumber,
        amount = this.amount,
    )

    fun apply(cardPayment: CardPayment) = apply{
        this.cardNumber = cardPayment.cardNumber
        this.amount = cardPayment.amount
    }

    companion object {

        fun fromDomain(cardPayment: CardPayment) = CardPaymentEntity(
            id = cardPayment.id,
            orderId = cardPayment.orderId,
            cardNumber = cardPayment.cardNumber,
            amount = cardPayment.amount,
        )
    }

}
