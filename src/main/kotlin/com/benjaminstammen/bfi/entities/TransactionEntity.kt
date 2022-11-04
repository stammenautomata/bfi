package com.benjaminstammen.bfi.entities

import java.math.BigDecimal
import java.sql.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "transactions")
class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
        protected set

    @Column(name = "transaction_date", nullable = false)
    var transactionDate: Date? = null

    @Column(name = "posted_date")
    var postedDate: Date? = null

    @Column(name = "amount", nullable = false, precision = 2, scale = 2)
    var amount: BigDecimal? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    var category: CategoryEntity? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    var merchant: MerchantEntity? = null

    @Lob
    @Column(name = "payee_description")
    var merchantDescription: String? = null

    @OneToOne
    @JoinColumn(name = "receipt_id", unique = true)
    var receipt: ReceiptEntity? = null
}