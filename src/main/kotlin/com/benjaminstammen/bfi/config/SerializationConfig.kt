package com.benjaminstammen.bfi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

@Configuration
class SerializationConfig {

    @Bean
    fun dateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yy"))
            .appendOptional(DateTimeFormatter.ISO_DATE)
            .toFormatter()
    }
}
