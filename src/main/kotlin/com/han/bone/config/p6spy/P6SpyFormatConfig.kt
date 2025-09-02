package com.han.bone.config.p6spy

import com.p6spy.engine.logging.Category.STATEMENT
import com.p6spy.engine.spy.P6SpyOptions
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import jakarta.annotation.PostConstruct
import org.hibernate.engine.jdbc.internal.FormatStyle.BASIC
import org.springframework.context.annotation.Configuration


@Configuration
class P6SpyFormatConfig : MessageFormattingStrategy {

    @PostConstruct
    fun setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(this::class.java.name)
    }

    override fun formatMessage(
        connectionId: Int,
        now: String,
        elapsed: Long,
        category: String,
        prepared: String,
        sql: String,
        url: String
    ): String {
        val formattedSql = formatSql(category, sql)
        return "[$category] | $elapsed ms | $formattedSql"
    }

    private fun formatSql(category: String, sql: String?): String? {
        sql?.let {
            if(STATEMENT.name == category){
                return BASIC.getFormatter().format(sql)
            }
        }
        return sql
    }
}