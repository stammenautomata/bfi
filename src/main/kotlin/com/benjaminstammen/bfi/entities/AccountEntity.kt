package com.benjaminstammen.bfi.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "accounts")
class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
        private set


    @Column(name = "name", nullable = false, unique = true)
    var name: String? = null


    @Lob
    @Column(name = "note")
    var note: String? = null
}