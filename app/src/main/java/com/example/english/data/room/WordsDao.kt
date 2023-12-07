package com.example.english.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Upsert
    suspend fun upsertWords(wordsEntity: WordsEntity)

    @Delete
    suspend fun deleteWords(wordsEntity: WordsEntity)

    @Query("SELECT * FROM words")
    fun getWords(): Flow <List<WordsEntity>>
}