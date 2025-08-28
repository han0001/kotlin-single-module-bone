package com.han.bone.config.filter

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class CachingRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val cached: ByteArray

    init {
        // 요청 초입에서 바디를 전부 읽어 메모리에 저장 (UTF-8 고정)
        val bytes = request.inputStream.readAllBytes()
        cached = bytes ?: ByteArray(0)
    }

    fun contentAsByteArray(): ByteArray = cached

    override fun getInputStream(): ServletInputStream {
        val bais = ByteArrayInputStream(cached)
        return object : ServletInputStream() {
            override fun isFinished(): Boolean = bais.available() == 0
            override fun isReady(): Boolean = true
            override fun setReadListener(readListener: ReadListener?) {}
            override fun read(): Int = bais.read()
        }
    }

    override fun getReader(): BufferedReader {
        val cs = StandardCharsets.UTF_8
        return BufferedReader(InputStreamReader(inputStream, cs))
    }
}