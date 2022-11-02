package com.benjaminstammen.bfi.translation

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.entities.MerchantEntity
import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.model.Category
import com.benjaminstammen.bfi.model.CreateTransactionRequest
import com.benjaminstammen.bfi.model.Merchant
import com.benjaminstammen.bfi.model.Transaction

fun fromEntity(entity: TransactionEntity): Transaction {
    val category = entity.category!!
    val merchant = entity.merchant!!
    return Transaction(
            id = entity.id!!,
            transactionDate = entity.transactionDate!!,
            postedDate = entity.postedDate,
            amount = entity.amount!!,
            category = Category(
                    id = category.id!!,
                    name = category.name!!,
                    note = category.note
            ),
            merchant = Merchant(
                    id = merchant.id!!,
                    name = merchant.name!!,
                    note = merchant.note
            ),
            merchantDescription = entity.merchantDescription
    )
}

fun toEntity(request: CreateTransactionRequest, category: CategoryEntity, merchant: MerchantEntity): TransactionEntity {
    val transactionEntity = TransactionEntity()
    transactionEntity.transactionDate = request.transactionDate
    transactionEntity.postedDate = request.postedDate
    transactionEntity.amount = request.amount
    transactionEntity.category = category
    transactionEntity.merchant = merchant
    transactionEntity.merchantDescription = request.merchantDescription
    return transactionEntity
}
