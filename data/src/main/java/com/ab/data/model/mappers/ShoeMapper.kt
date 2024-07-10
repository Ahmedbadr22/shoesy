package com.ab.data.model.mappers

import com.ab.data.model.dto.ShoeDto
import com.ab.domain.model.data.Shoe

fun ShoeDto.toDomain() = Shoe(
    id = id,
    name = name,
    description = description,
    price = price,
    image = image,
    brand = brand.toDomain(),
    colors = colors.toDomainList(),
    sizes = sizes,
    quantity = quantity,
    isFavorite = isFavorite
)

fun List<ShoeDto>.toDomainList() = map(ShoeDto::toDomain)