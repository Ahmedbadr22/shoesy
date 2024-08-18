package com.ab.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ab.core.constants.DB

@Entity(tableName = DB.USER_TABLE)
data class ReviewerEntity(
    @PrimaryKey
    val email: String,
    val profileImage: String?,
    val fullName: String,
)
