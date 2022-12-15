package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.AccountEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface AccountRepository: MongoRepository<AccountEntity, String> {
    @Query("{name: '?0'}")
    fun findAccountByName(name: String);
}
