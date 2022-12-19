package com.benjaminstammen.bfi

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "BFI Swagger Title",
        version = "0.1",
        description = "BFI description"))
@ConfigurationPropertiesScan
@EnableMongoRepositories
class BfiApplication

fun main(args: Array<String>) {
    runApplication<BfiApplication>(*args)
}


