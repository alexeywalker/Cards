//package com.example.english.data.room
//
//import com.example.english.presentation.WordsState
//import com.example.english.domain.repository.WordsRepository
//import kotlinx.coroutines.flow.Flow
//
//class WordsRepositoryImpl(private val dao: WordsDao): WordsRepository {
//
//    override suspend fun insertAll(words: WordsState) {
//        dao.insertAll()
//    }
//
//    override suspend fun delete() {
//        dao.delete()
//    }
//
//    override fun getAll(): Flow<List<WordsEntity>> {
//        dao.getAll()
//    }
//}