package com.benjaminstammen.bfi.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "application")
class ApplicationProperties(
    val clientOriginUrl: String
)
