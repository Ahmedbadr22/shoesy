package com.ab.shoesy.di

import com.ab.data.dao.BrandDao
import com.ab.data.dao.CartDao
import com.ab.data.dao.ColorDao
import com.ab.data.dao.ReviewDao
import com.ab.data.dao.ReviewerDao
import com.ab.data.dao.ShoeColorCrossRefDao
import com.ab.data.dao.ShoeDao
import com.ab.data.db.AppDatabase
import org.koin.dsl.module

val daoModule = module {

    single<BrandDao> {
        val database : AppDatabase = get()
        database.getBrandDao()
    }

    single<ShoeDao> {
        val database : AppDatabase = get()
        database.getShoeDao()
    }

    single<ColorDao> {
        val database : AppDatabase = get()
        database.getColorDao()
    }

    single<ShoeColorCrossRefDao> {
        val database : AppDatabase = get()
        database.getShoeColorCrossRefDao()
    }

    single<ReviewDao> {
        val database : AppDatabase = get()
        database.getReviewDao()
    }

    single<ReviewerDao> {
        val database : AppDatabase = get()
        database.getReviewerDao()
    }

    single<CartDao> {
        val database : AppDatabase = get()
        database.getCartDao()
    }
}