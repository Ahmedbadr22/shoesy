package com.ab.data.model.mappers

import com.ab.data.model.dto.ColorDto
import com.ab.domain.model.data.Color
import com.ab.data.model.entity.ColorEntity

fun ColorDto.toEntity() = ColorEntity(id, name, hex)

fun List<ColorDto>.toEntities() = map { color -> color.toEntity() }


fun ColorEntity.toDomain() = Color(colorId, name, hex)

fun List<ColorEntity>.toDomainList() = map(ColorEntity::toDomain)