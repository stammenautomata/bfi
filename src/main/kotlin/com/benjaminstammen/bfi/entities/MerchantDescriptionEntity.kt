package com.benjaminstammen.bfi.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "merchant_descriptions")
class MerchantDescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "merchant_id", nullable = false)
    var merchant: MerchantEntity? = null
}