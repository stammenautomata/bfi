package com.benjaminstammen.bfi

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.model.CategoryMutableProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.hamcrest.Matchers
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
class CategoryIntegrationTests(
    @Autowired val webApplicationContext: WebApplicationContext
) {
    val mockMvc: MockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .build()
    val mapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule.Builder().build())

    lateinit var persistedObject: CategoryEntity

    @BeforeEach
    fun setup() {
        val createCategoryRequest = CategoryMutableProperties(
            name = "testName",
            note = "testNote"
        )
        val response = mockMvc.perform(
            post("/categories")
                .content(mapper.writeValueAsString(createCategoryRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .response
            .contentAsString
        persistedObject = mapper.readValue(response, CategoryEntity::class.java)
    }

    @AfterEach
    fun cleanup() {
        mockMvc.perform(
            delete("/categories/${persistedObject.id}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()
    }

    @Test
    fun `test category LIST`() {
        mockMvc.perform(
            get("/categories")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(mapper.writeValueAsString(listOf(persistedObject))))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    fun `test category UPDATE`() {
        val updateRequestBody = CategoryMutableProperties("newName", "newNote")
        val updatedCategory = persistedObject.mergeWithProperties(updateRequestBody)

        mockMvc.perform(
            post("/categories/${persistedObject.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updateRequestBody))
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(updatedCategory)))
    }
}
