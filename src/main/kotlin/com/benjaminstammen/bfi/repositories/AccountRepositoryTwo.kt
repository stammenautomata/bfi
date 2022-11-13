package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface AccountRepositoryTwo: MongoRepository<Account, String> {
    @Query("{name: '?0'}")
    fun findAccountByName(name: String);
}
