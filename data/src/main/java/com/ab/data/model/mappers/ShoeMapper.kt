package com.ab.data.model.mappers

import com.ab.data.model.dto.ShoeDto
import com.ab.data.model.entity.ReviewEntity
import com.ab.data.model.entity.ReviewerEntity
import com.ab.data.model.entity.ShoeColorsCrossRef
import com.ab.data.model.entity.ShoeEntity
import com.ab.data.model.entity.ShoeWithBrandsAndColors
import com.ab.domain.model.data.Shoe

fun ShoeDto.toEntity() = ShoeEntity(
    id = id,
    name = name,
    description = description,
    price = price,
    image = image,
    brandId = brand.id,
    quantity = quantity,
    isFavorite = isFavorite,
    sizes = sizes.joinToString(",")
)

fun List<ShoeDto>.toEntities() = map(ShoeDto::toEntity)

fun List<ShoeDto>.toShoeColorCrossRefList() : List<ShoeColorsCrossRef> = flatMap { shoeDto ->
    shoeDto.colors.map { colorDto -> ShoeColorsCrossRef(shoeDto.id, colorDto.id) }
}

fun List<ShoeDto>.getReviewsEntities() : List<ReviewEntity> = flatMap { shoeDto ->
    shoeDto.reviews.toEntities(shoeDto.id)
}

fun List<ShoeDto>.getReviewerEntities() : List<ReviewerEntity> = flatMap { shoeDto ->
    shoeDto.reviews.getUsers().toEntities()
}

fun ShoeWithBrandsAndColors.toDomain() = Shoe(
    id = shoeEntity.id,
    name = shoeEntity.name,
    description = shoeEntity.description,
    price = shoeEntity.price,
    image = shoeEntity.image,
    brand = brandEntity.toDomain(),
    quantity = shoeEntity.quantity,
    isFavorite = shoeEntity.isFavorite,
    colors = colorEntities.toDomainList(),
    sizes = shoeEntity.sizes.split(",").map { char -> char.toInt() },
    reviews = reviewEntities.toDomainList()
)

fun List<ShoeWithBrandsAndColors>.toDomainList() = map(ShoeWithBrandsAndColors::toDomain)