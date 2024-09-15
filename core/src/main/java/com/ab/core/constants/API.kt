package com.ab.core.constants


object API {
    // Base Url
    const val BASE_URL = "https://225c-41-43-154-163.ngrok-free.app"

    // App
    private const val AUTH_APP = "/auth"
    private const val BRAND_APP = "/brand"
    private const val PRODUCT_APP = "/product"
    private const val FAVORITE_APP = "/favorite"
    private const val COLOR_APP = "/color"
    private const val CART_APP = "/cart"
    private const val MASTER_APP = "/master"

    // Path
    const val ID_PATH = "id"

    // Endpoints
    // Auth
    const val LOGIN_ENDPOINT = "$AUTH_APP/login"
    // Brand
    const val LIST_BRANDS_ENDPOINT = "$BRAND_APP/list"
    // Product
    const val LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT = "$PRODUCT_APP/list-special-shoes-for-you"
    const val LIST_ALL_PRODUCT_ENDPOINT = "$PRODUCT_APP/list"
    const val LIST_SHOES_BY_BRAND_ID = "$PRODUCT_APP/list-shoes-by-brand-id/{$ID_PATH}"
    const val LIST_USER_FAVORITE_SHOES = "$PRODUCT_APP/list-user-favorites"
    const val GET_SHOE_BY_ID = "$PRODUCT_APP/get-shoe-by-id/{$ID_PATH}"
    // Favorite
    const val POST_FAVORITE_SHOE = "$FAVORITE_APP/add-favorite"
    const val DELETE_FAVORITE_SHOE = "$FAVORITE_APP/delete-favorite/{$ID_PATH}"
    // Color
    const val LIST_COLORS_ENDPOINT = "$COLOR_APP/list"

    // Cart
    const val POST_CART_ITEM_ENDPOINT = "$CART_APP/create"
    const val LIST_CART_ITEMS_ENDPOINT = "$CART_APP/list"
    const val UPDATE_CART_ITEM_QUANTITY_ENDPOINT = "$CART_APP/update/{$ID_PATH}"
    const val DELETE_CART_ITEM_ENDPOINT = "$CART_APP/delete/{$ID_PATH}"

    // Master
    const val GET_MASTER_DATA_ENDPOINT = "$MASTER_APP/list-master"
}



