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

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        MDC.put("requestId", UUID.randomUUID().toString().replace("-", "").substring(0, 8))
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
            MDC.remove("requestId")
        }
    }

    companion object {
        const val DEFAULT_ORDER = Int.MIN_VALUE + 10
    }
}