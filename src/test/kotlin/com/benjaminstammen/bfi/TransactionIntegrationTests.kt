package com.benjaminstammen.bfi

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [MongoInitializer::class])
class TransactionIntegrationTests(@Autowired val mockMvc: MockMvc) {

    @BeforeEach
    fun setup() {
        // TODO
    }
}
