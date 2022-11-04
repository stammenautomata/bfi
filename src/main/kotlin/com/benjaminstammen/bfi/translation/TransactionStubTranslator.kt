package com.benjaminstammen.bfi.translation

import com.benjaminstammen.bfi.entities.ReceiptEntity
import com.benjaminstammen.bfi.entities.TransactionStubEntity
import com.benjaminstammen.bfi.model.CreateTransactionStubRequest
import com.benjaminstammen.bfi.model.TransactionStub

fun fromEntity(entity: TransactionStubEntity): TransactionStub {
    val entityReceipt = entity.receipt
    val receipt = if (entityReceipt != null) fromEntity(entityReceipt) else null
    return TransactionStub(
        id = entity.id!!,
        addedDate = entity.addedDate!!,
        amount = entity.amount!!,
        note = entity.note,
        receipt = receipt
    )
}

fun toEntity(request: CreateTransactionStubRequest, receipt: ReceiptEntity): TransactionStubEntity {
    val transactionStubEntity = TransactionStubEntity()
    transactionStubEntity.amount = request.amount
    transactionStubEntity.note = request.note
    transactionStubEntity.receipt = receipt
    return transactionStubEntity
}
