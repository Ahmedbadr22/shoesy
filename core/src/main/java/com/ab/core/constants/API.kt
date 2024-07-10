package com.ab.core.constants


object API {
    // Base Url
    const val BASE_URL = "https://cb23-41-233-17-196.ngrok-free.app"

    // App
    private const val AUTH_APP = "/auth"
    private const val BRAND_APP = "/brand"
    private const val PRODUCT_APP = "/product"
    private const val FAVORITE_APP = "/favorite"

    // Path
    const val ID_PATH = "id"

    // Endpoints
    // Auth
    const val LOGIN_ENDPOINT = "$AUTH_APP/login"
    // Brand
    const val LIST_BRANDS_ENDPOINT = "$BRAND_APP/list"
    // Product
    const val LIST_SPECIAL_PRODUCT_FOR_YOU_ENDPOINT = "$PRODUCT_APP/list-special-shoes-for-you"
    const val LIST_SHOES_BY_BRAND_ID = "$PRODUCT_APP/list-shoes-by-brand-id/{$ID_PATH}"
    const val LIST_USER_FAVORITE_SHOES = "$PRODUCT_APP/list-user-favorites"
    const val GET_SHOE_BY_ID = "$PRODUCT_APP/get-shoe-by-id/{$ID_PATH}"
    // Favorite
    const val POST_FAVORITE_SHOE = "$FAVORITE_APP/add-favorite"
    const val DELETE_FAVORITE_SHOE = "$FAVORITE_APP/delete-favorite/{$ID_PATH}"
}



