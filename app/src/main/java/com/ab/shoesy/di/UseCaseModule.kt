package com.ab.shoesy.di

import com.ab.domain.usecases.auth.GetAccessTokenUseCase
import com.ab.domain.usecases.auth.IsAuthenticatedUserUseCase
import com.ab.domain.usecases.auth.LoginUseCase
import com.ab.domain.usecases.brand.ListBrandsFromRemoteToLocalUseCase
import com.ab.domain.usecases.cart.AddCartItemUseCase
import com.ab.domain.usecases.cart.DeleteCartItemByIdUseCase
import com.ab.domain.usecases.cart.GetCartItemByShoeIdIfExistOrNullFlowUseCase
import com.ab.domain.usecases.cart.GetCartItemCountUseCase
import com.ab.domain.usecases.cart.ListCartItemsFlowUseCase
import com.ab.domain.usecases.cart.UpdateCartItemQuantityUseCase
import com.ab.domain.usecases.master.DownloadMasterDataUseCase
import com.ab.domain.usecases.product.GetFavoriteShoeCountUseCase
import com.ab.domain.usecases.product.GetShoeByIdUseCase
import com.ab.domain.usecases.product.ListShoesByBrandIdUseCase
import com.ab.domain.usecases.product.ListSpecialShoeForYouUseCase
import com.ab.domain.usecases.product.ListUserFavoriteShoesUseCases
import com.ab.domain.usecases.product.MarkShoeAsFavoriteByIdUseCase
import com.ab.domain.usecases.product.MarkShoeAsUnFavoriteByIdUseCase
import com.ab.domain.usecases.utils.EmailValidationUseCase
import com.ab.domain.usecases.utils.PasswordValidationUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::LoginUseCase)
    singleOf(::EmailValidationUseCase)
    singleOf(::PasswordValidationUseCase)
    singleOf(::ListBrandsFromRemoteToLocalUseCase)
    singleOf(::ListSpecialShoeForYouUseCase)
    singleOf(::ListShoesByBrandIdUseCase)
    singleOf(::GetShoeByIdUseCase)
    singleOf(::GetAccessTokenUseCase)
    singleOf(::ListUserFavoriteShoesUseCases)
    singleOf(::MarkShoeAsFavoriteByIdUseCase)
    singleOf(::MarkShoeAsUnFavoriteByIdUseCase)
    singleOf(::IsAuthenticatedUserUseCase)
    singleOf(::DownloadMasterDataUseCase)
    singleOf(::GetFavoriteShoeCountUseCase)
    singleOf(::ListCartItemsFlowUseCase)
    singleOf(::UpdateCartItemQuantityUseCase)
    singleOf(::DeleteCartItemByIdUseCase)
    singleOf(::GetCartItemCountUseCase)
    singleOf(::AddCartItemUseCase)
    singleOf(::GetCartItemByShoeIdIfExistOrNullFlowUseCase)
}