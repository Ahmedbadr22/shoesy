package com.ab.data.source.local.cart

import android.util.Log
import com.ab.data.dao.CartDao
import com.ab.data.model.entity.CartEntity
import com.ab.data.model.entity.CartItemWithShoeAndColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CartLocalDataSourceImpl(
    private val cartDao: CartDao
): CartLocalDataSource {
    override suspend fun insert(cartEntity: List<CartEntity>) {
        withContext(Dispatchers.IO) {
            cartDao.insert(cartEntity)
        }
    }

    override fun listAsFlow(): Flow<List<CartItemWithShoeAndColor>> = cartDao.listAllAsFlow()

    override suspend fun updateQuantityById(id: Int, quantity: Int) {
        withContext(Dispatchers.IO) {
            cartDao.updateQuantityById(id, quantity)
        }
    }

    override suspend fun deleteById(id: Int) {
        Log.i("AHMED_BADR", "deleteById: Delete $id")
        withContext(Dispatchers.IO) {
            cartDao.deleteById(id)
        }
    }

    override fun getCountFlow(): Flow<Int> = cartDao.getCountFlow()

}