package com.ab.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.ab.core.constants.DB

@Entity(tableName = DB.CART_TABLE)
data class CartEntity(
    @PrimaryKey
    val id: Int = 0,
    val shoeId: Int,
    val colorId: Int,
    val quantity: Int,
    val size: Int
)


class CartItemWithShoeAndColor(
    @Embedded val cartEntity: CartEntity,
    @Relation(
        parentColumn = "shoeId",
        entityColumn = "id",
        entity = ShoeEntity::class
    )
    val shoeWithBrand: ShoeWithBrand,
    @Relation(
        parentColumn = "colorId",
        entityColumn = "colorId"
    )
    val colorEntity: ColorEntity
)
