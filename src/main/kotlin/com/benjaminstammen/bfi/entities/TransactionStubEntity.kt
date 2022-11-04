package com.benjaminstammen.bfi.entities

import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.sql.Date
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "transaction_stubs")
class TransactionStubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
        protected set

    @CreationTimestamp
    @Column(name = "added_date", nullable = false)
    var addedDate: Date? = null

    @Column(name = "amount", nullable = true, precision = 2, scale = 2)
    var amount: BigDecimal? = null

    @Lob
    @Column(name = "note")
    var note: String? = null

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "receipt_id", unique = true)
    var receipt: ReceiptEntity? = null
}