package com.benjaminstammen.bfi.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "receipts")
class ReceiptEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
        protected set

    @Column(name = "image_path")
    open var imagePath: String? = null

    // TODO: This should probably be a binary type or something
    //  along those lines, but not spending time on that for now.
    @Column(name = "image_md5", nullable = false)
    open var imageMd5: String? = null
}