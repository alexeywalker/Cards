package com.example.english.domain.repository

import com.example.english.data.room.WordsEntity
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    suspend fun insertAll()

    suspend fun delete()

    fun getAll(): Flow<List<WordsEntity>>
}