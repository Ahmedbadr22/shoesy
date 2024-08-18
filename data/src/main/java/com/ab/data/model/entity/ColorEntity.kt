package com.ab.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ab.core.constants.DB

@Entity(tableName = DB.COLOR_TABLE)
data class ColorEntity(
    @PrimaryKey
    val colorId: Int,
    val name: String,
    val hex: String,
)