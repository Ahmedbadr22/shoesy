package com.ab.data.model.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.ab.core.constants.DB

@Entity(tableName = DB.SHOE_TABLE)
data class ShoeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val quantity: Int,
    val image: String,
    val sizes: String,
    val isFavorite: Boolean,
    val brandId: Int
)

@Entity(
    tableName = DB.SHOE_COLOR_TABLE,
    primaryKeys = ["id", "colorId"],
    indices = [Index(value = ["colorId"])]
)
data class ShoeColorsCrossRef(
    val id: Int,
    val colorId: Int
)


data class ShoeWithBrand(
    @Embedded val shoeEntity: ShoeEntity,
    @Relation(
        parentColumn = "brandId",
        entityColumn = "id"
    )
    val brandEntity: BrandEntity,
)


data class ShoeWithBrandsAndColors(
    @Embedded val shoeEntity: ShoeEntity,
    @Relation(
        parentColumn = "brandId",
        entityColumn = "id"
    )
    val brandEntity: BrandEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "colorId",
        associateBy = Junction(ShoeColorsCrossRef::class)
    )
    val colorEntities: List<ColorEntity>,
    @Relation(
        entity = ReviewEntity::class,
        parentColumn = "id",
        entityColumn = "shoeId",
    )
    val reviewEntities: List<ReviewWithReviewer>
)

