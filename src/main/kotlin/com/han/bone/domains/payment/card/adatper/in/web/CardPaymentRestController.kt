package com.han.bone.domains.payment.card.adatper.`in`.web

import com.han.bone.common.response.ApiResponse
import com.han.bone.domains.payment.card.adatper.`in`.web.dto.request.CardPaymentCreateRequest
import com.han.bone.domains.payment.card.adatper.`in`.web.dto.response.CardPaymentCreateResponse
import com.han.bone.domains.payment.card.adatper.`in`.web.dto.response.CardPaymentResponse
import com.han.bone.domains.payment.card.application.port.`in`.CardPaymentUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/{customerId}/payment/card")
class CardPaymentRestController(
    private val cardPaymentUseCase: CardPaymentUseCase,
) {

    @GetMapping("/{orderId}")
    fun findById(
        @PathVariable customerId: String,
        @PathVariable orderId: String,
    ): ApiResponse<CardPaymentResponse> {
        val response = cardPaymentUseCase.findByOrderId(orderId)
        return ApiResponse.success(response)
    }

    @PostMapping
    fun createCardPayment(
        @PathVariable customerId: String,
        @RequestBody request: CardPaymentCreateRequest,
    ): ApiResponse<CardPaymentCreateResponse> {
        val response = cardPaymentUseCase.create(request.toCommand())
        return ApiResponse.success(response)
    }
}