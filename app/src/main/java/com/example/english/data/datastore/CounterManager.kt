package com.example.english.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class CounterManager(context: Context) {

    private val dataStore = context.dataStore
    private val counterKey = intPreferencesKey("counter")

    suspend fun getCounter(): Int {
        val preferences = dataStore.data.first()
        return preferences[counterKey] ?: 0
    }

    suspend fun incrementCounter() {
        dataStore.edit { preferences ->
            val currentCounter = preferences[counterKey] ?: 0
            preferences[counterKey] = currentCounter + 1
        }
    }
}

