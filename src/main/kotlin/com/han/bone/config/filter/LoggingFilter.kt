package com.han.bone.config.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.util.UUID
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.measureTime



class LoggingFilter : OncePerRequestFilter() {

    /**
     * 로깅을 적용하지 않을 요청을 지정
     * true를 반환하면 해당 요청은 doFilterInternal()이 실행되지 않음
     */
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val uri = request.requestURI
        return uri.startsWith("/actuator/prometheus") ||
                uri.startsWith("/actuator/health") ||
                uri.startsWith("/actuator/metrics")
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        MDC.put("reqId", UUID.randomUUID().toString().replace("-", "").substring(0, 8))
        val cachingRequest = CachingRequestWrapper(request)
        val cachingResponse = ContentCachingResponseWrapper(response)

        if (isLoggingEnabled(cachingRequest)) {
            requestLog(cachingRequest)
        }

        var elapsed: Duration
        try {
            elapsed = measureTime {
                chain.doFilter(cachingRequest, cachingResponse)
            }

            if (isLoggingEnabled(cachingRequest)) {
                responseLog(cachingResponse, cachingRequest, elapsed)
            }
        } finally {
            cachingResponse.copyBodyToResponse()
            MDC.remove("reqId")
        }
    }

    companion object {
        const val DEFAULT_ORDER = Int.MIN_VALUE + 10
    }
}