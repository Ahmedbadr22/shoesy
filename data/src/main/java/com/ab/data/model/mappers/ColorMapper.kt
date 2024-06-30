package com.ab.data.model.mappers

import com.ab.data.model.dto.BrandDto
import com.ab.data.model.dto.ColorDto
import com.ab.domain.model.data.Brand
import com.ab.domain.model.data.Color

fun ColorDto.toDomain() = Color(id, name, hex)

fun List<ColorDto>.toDomainList() = map(ColorDto::toDomain)