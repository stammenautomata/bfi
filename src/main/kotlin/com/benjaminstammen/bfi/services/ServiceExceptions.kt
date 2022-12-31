package com.benjaminstammen.bfi.services

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

open class ResourceNotFoundException(resourceName: String, resourceId: String) :
    ResponseStatusException(HttpStatus.NOT_FOUND, "$resourceName with ID '$resourceId' does not exist.")

class AccountNotFoundException(accountId: String) : ResourceNotFoundException("Account", accountId)
class MerchantNotFoundException(merchantId: String) : ResourceNotFoundException("Merchant", merchantId)
class CategoryNotFoundException(categoryId: String): ResourceNotFoundException("Category", categoryId)
