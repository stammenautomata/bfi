package com.benjaminstammen.bfi

import com.benjaminstammen.bfi.entities.AccountEntity
import com.benjaminstammen.bfi.model.AccountMutableProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = [MongoInitializer::class])
class AccountIntegrationTests(
    @Autowired val webApplicationContext: WebApplicationContext
) {
    val mockMvc: MockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .build()
    val mapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule.Builder().build())

    lateinit var persistedObject: AccountEntity

    @BeforeEach
    fun setup() {
        val createAccountRequest = AccountMutableProperties(
            name = "testName",
            autoTags = listOf("testTagOne", "testTagTwo"),
            note = "testNote"
        )
        val response = mockMvc.perform(
            post("/account")
                .content(mapper.writeValueAsString(createAccountRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .response
            .contentAsString
        persistedObject = mapper.readValue(response, AccountEntity::class.java)
    }

    @AfterEach
    fun cleanup() {
        mockMvc.perform(
            delete("/account/${persistedObject.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()
    }

    @Test
    fun `test account LIST`() {
        mockMvc.perform(
            get("/account")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(mapper.writeValueAsString(listOf(persistedObject))))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    fun `test account UPDATE`() {
        val updateRequestBody = AccountMutableProperties("newName", listOf("newTagOne", "newTagTwo"), "newNote")
        val updatedAccount = persistedObject.mergeWithProperties(updateRequestBody)

        mockMvc.perform(
            post("/account/${persistedObject.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateRequestBody))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(updatedAccount)))
    }
}
