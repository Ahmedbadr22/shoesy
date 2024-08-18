package com.ab.data.model.mappers

import com.ab.data.model.dto.BrandDto
import com.ab.data.model.entity.BrandEntity
import com.ab.domain.model.data.Brand

fun BrandDto.toEntity() = BrandEntity(id, name, image, stockItemCount)

fun List<BrandDto>.toEntities() = map(BrandDto::toEntity)


fun BrandEntity.toDomain() = Brand(id, name, image)

fun List<BrandEntity>.toDomainList() = map(BrandEntity::toDomain)