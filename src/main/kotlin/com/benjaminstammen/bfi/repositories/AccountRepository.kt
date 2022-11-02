package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.AccountEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: CrudRepository<AccountEntity, String>