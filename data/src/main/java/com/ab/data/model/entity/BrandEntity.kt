package com.ab.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ab.core.constants.DB

@Entity(tableName = DB.BRAND_TABLE)
data class BrandEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val stockItemCount: Int,
)
