package com.ab.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.ab.core.constants.DB

@Entity(tableName = DB.REVIEW_TABLE)
data class ReviewEntity(
    @PrimaryKey
    val id: Int,
    val userEmail: String,
    val rating: Int,
    val review: String,
    val shoeId: Int
)


data class ReviewWithReviewer(
    @Embedded val reviewEntity: ReviewEntity,
    @Relation(
        parentColumn = "userEmail",
        entityColumn = "email"
    )
    val reviewerEntity: ReviewerEntity
)
