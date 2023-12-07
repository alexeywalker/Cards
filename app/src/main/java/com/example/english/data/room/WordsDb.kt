package com.example.english.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WordsEntity::class],
    version = 1
)
abstract class WordsDb : RoomDatabase() {
    abstract val dao: WordsDao
}