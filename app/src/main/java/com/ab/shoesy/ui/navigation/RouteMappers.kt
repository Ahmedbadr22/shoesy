package com.ab.shoesy.ui.navigation

import com.ab.domain.model.data.Brand

fun Screen.Brand.toData() = Brand(id, name, image)
fun Brand.toRoute() = Screen.Brand(id, name, image)
