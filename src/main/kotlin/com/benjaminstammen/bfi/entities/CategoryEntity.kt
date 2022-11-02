package com.benjaminstammen.bfi.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "categories")
open class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
        protected set


    @Column(name = "name", nullable = false, unique = true)
    open var name: String? = null


    @Lob
    @Column(name = "note")
    open var note: String? = null
}