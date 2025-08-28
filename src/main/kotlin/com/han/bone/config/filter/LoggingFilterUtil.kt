package com.han.bone.config.filter

import com.han.bone.util.logging.log
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.Charset
import kotlin.time.Duration


private val excludedPaths = listOf(
    "/webjars", ".swf", ".ico", ".eot", ".woff2", ".gif",
    ".jpg", ".png", ".js", ".css", ".jsp", "/js/ko", "/custs/agreement-agents"
)

fun isLoggingEnabled(request: HttpServletRequest): Boolean {
    val resourcePath = request.requestURI.lowercase()
    return excludedPaths.none { resourcePath.startsWith(it) }
}

fun requestLog(httpRequest: CachingRequestWrapper) {

    log.info { "┌────────────────────────────┐" }
    log.info { "├─@Request Log" }
    log.info { "├─@URI ▶[${httpRequest.requestURI}]" }
    log.info { "├─@Request Log" }
    log.info { "├─@Method : ${httpRequest.method}" }
    log.info { "├─@RequestURL : ${httpRequest.requestURL}" }
    log.info { "├─@RemoteAddr : ${httpRequest.remoteAddr}" }
    log.info { "├─@Header : ${httpRequest.headerNames.toList().associateWith { httpRequest.getHeader(it) }}" }
    log.info { "├─@parameter : ${httpRequest.parameterNames.toList().associateWith { httpRequest.getParameter(it) }}" }
    val body = httpRequest.contentAsByteArray()
        .takeIf { it.isNotEmpty() }
        ?.toString(Charsets.UTF_8)
        ?.replace(System.lineSeparator(), "") ?: ""
    log.info { "├─@Body : ${body} "}
    log.info { "└────────────────────────────┘" }
}

fun responseLog(httpResponseWrapper: ContentCachingResponseWrapper, httpRequest: HttpServletRequest, processingTime: Duration) {
    log.info { "┌────────────────────────────┐" }
    log.info { "├─@Response Log" }
    log.info { "├─@ProcessingTime : ${processingTime}" }
    log.info { "├─@Status : ${httpResponseWrapper.status}" }
    log.info { "├─@Content-Type : ${httpResponseWrapper.contentType}" }
    log.info { "├─@Header : ${httpResponseWrapper.headerNames.toList().associateWith { httpResponseWrapper.getHeaders(it) }}" }
    val body = httpResponseWrapper.contentAsByteArray.toString(Charsets.UTF_8)
    log.info { "├─@Body : ${if (httpResponseWrapper.contentType?.contains("application/json") == true) body else "Body Log..."}" }
    log.info { "├─@END ◀[${httpRequest.requestURI}]" }
    log.info { "└────────────────────────────┘" }
}