//package com.example.english.presentation
//
//import com.example.english.data.room.WordsEntity
//
//sealed interface WordsEvent{
//    object SaveWords: WordsEvent
//    object ShowAddNewWordsScreen: WordsEvent
//    object HideAddNewWordsScreen: WordsEvent
//
//    data class SetNewWords (val newWords: String): WordsEvent
//    data class SetTranslateWords (val translateWords: String): WordsEvent
//    data class SetNote (val note: String): WordsEvent
//    data class DeleteWords (val wordsEntity: WordsEntity): WordsEvent
//}