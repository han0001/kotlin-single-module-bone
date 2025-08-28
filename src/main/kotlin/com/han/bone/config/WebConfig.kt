package com.han.bone.config

import com.han.bone.config.filter.LoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfiguration(
) : WebMvcConfigurer {

    @Bean
    fun loggingFilter(): FilterRegistrationBean<LoggingFilter> {
        return FilterRegistrationBean(LoggingFilter()).apply {
            order = LoggingFilter.DEFAULT_ORDER
            urlPatterns = listOf("/*")
        }
    }

}