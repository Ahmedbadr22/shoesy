package com.ab.domain.repository



interface ColorRepository {
    suspend fun listFromRemoteAndInsertToLocal(token: String)
}