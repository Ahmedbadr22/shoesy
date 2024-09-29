package com.ab.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


suspend fun DataStore<Preferences>.getIntFlow(key: String): Int? {
    val booleanKey = intPreferencesKey(key)
    val result = data.first()
    return result[booleanKey]

}

suspend fun DataStore<Preferences>.setInt(key: String, value: Int) {
    val booleanKey = intPreferencesKey(key)
    edit { preferences ->
        preferences[booleanKey] = value
    }
}

suspend fun DataStore<Preferences>.getBooleanFlow(key: String): Boolean {
    val booleanKey = booleanPreferencesKey(key)
    val result = data.first()
    return result[booleanKey] ?: false

}

fun DataStore<Preferences>.getBooleanFlow(key: String, defaultValue: Boolean): Flow<Boolean> {
    val booleanKey = booleanPreferencesKey(key)
    return data.map { preferences: Preferences ->
        preferences[booleanKey] ?: defaultValue
    }
}

suspend fun DataStore<Preferences>.setBoolean(key: String, value: Boolean) {
    val booleanKey = booleanPreferencesKey(key)
    edit { preferences ->
        preferences[booleanKey] = value
    }

}

suspend fun DataStore<Preferences>.getString(key: String): String? {
    val booleanKey = stringPreferencesKey(key)
    val result = data.first()
    return result[booleanKey]

}

fun DataStore<Preferences>.getStringFlow(key: String, defaultValue: String): Flow<String?> {
    val stringKey = stringPreferencesKey(key)
    return data.map { preferences: Preferences ->
        preferences[stringKey] ?: defaultValue
    }
}

suspend fun DataStore<Preferences>.setString(key: String, value: String) {
    val booleanKey = stringPreferencesKey(key)
    edit { preferences ->
        preferences[booleanKey] = value
    }
}

suspend fun DataStore<Preferences>.deleteString(key: String) {
    val stringKey = stringPreferencesKey(key)
    edit { preferences ->
        preferences.remove(stringKey)
    }
}

suspend fun DataStore<Preferences>.deleteInt(key: String) {
    val intKey = intPreferencesKey(key)
    edit { preferences ->
        preferences.remove(intKey)
    }
}

suspend fun DataStore<Preferences>.deleteBoolean(key: String) {
    val boolKey = booleanPreferencesKey(key)
    edit { preferences ->
        preferences.remove(boolKey)
    }
}