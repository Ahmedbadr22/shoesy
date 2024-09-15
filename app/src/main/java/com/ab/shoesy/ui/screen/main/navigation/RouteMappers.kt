package com.ab.shoesy.ui.screen.main.navigation

import com.ab.domain.model.data.Brand

fun Screen.Brand.toData() = Brand(id, name, image)
fun Brand.toRoute() = Screen.Brand(id, name, image)
