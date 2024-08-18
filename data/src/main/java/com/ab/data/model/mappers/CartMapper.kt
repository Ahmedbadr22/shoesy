package com.ab.data.model.mappers

import com.ab.data.model.dto.CartDto
import com.ab.data.model.entity.CartEntity
import com.ab.data.model.entity.CartItemWithShoeAndColor
import com.ab.data.model.request.CartItemQuantityRequest
import com.ab.domain.model.data.CartItem
import com.ab.domain.model.data.CartItemQuantity

fun  CartDto.toEntity() = CartEntity(id, shoe, color, quantity, size)
fun  List<CartDto>.toEntities() = map(CartDto::toEntity)


fun  CartItemWithShoeAndColor.toDomain() = CartItem(
    id = cartEntity.id,
    shoeId = shoeWithBrand.shoeEntity.id,
    shoePrice = shoeWithBrand.shoeEntity.price,
    shoeName = shoeWithBrand.shoeEntity.name,
    shoeBrand = shoeWithBrand.brandEntity.toDomain(),
    shoeImage = shoeWithBrand.shoeEntity.image,
    color = colorEntity.toDomain(),
    quantity = cartEntity.quantity,
    size = cartEntity.size
)
fun  List<CartItemWithShoeAndColor>.toDomain() = map(CartItemWithShoeAndColor::toDomain)


fun CartItemQuantity.toRequest() = CartItemQuantityRequest(shoe, quantity)