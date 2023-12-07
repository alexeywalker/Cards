package com.example.english.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "word_original") val wordOriginal: String,
    @ColumnInfo(name = "word_translate") val wordTranslate: String,
    val note: String
)


//{
//    fun toWordEntity(): WordsEntity = WordsEntity(
//        id = id,
//        wordOriginal = wordOriginal,
//        wordTranslate = wordTranslate,
//        note = note
//    )
//
//    fun toWord(): WordsState = WordsState(
//        id = id,
//        wordOriginal = wordOriginal,
//        wordTranslate = wordTranslate,
//        note = note
//    )
//}
