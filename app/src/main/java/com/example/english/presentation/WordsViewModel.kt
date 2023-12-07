//package com.example.english.presentation
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.english.data.room.WordsDao
//import com.example.english.data.room.WordsEntity
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//class WordsViewModel(private val dao: WordsDao) : ViewModel() {
//    private val _state = MutableStateFlow(WordsState())
//
//    fun onEvent(event: WordsEvent) {
//        when (event) {
//            is WordsEvent.DeleteWords -> {
//                viewModelScope.launch { dao.deleteWords(event.wordsEntity) }
//
//            }
//
//            WordsEvent.HideAddNewWordsScreen -> {
//                _state.update { it.copy(isAddNewWord = false) }
//            }
//
//            WordsEvent.ShowAddNewWordsScreen -> {
//                _state.update { it.copy(isAddNewWord = true) }
//            }
//
//            WordsEvent.SaveWords -> {
//                val wordOriginal = _state.value.wordOriginal
//                val wordTranslate = _state.value.wordTranslate
//                val note = _state.value.note
//
//                if (wordOriginal.isBlank() || wordTranslate.isBlank() || note.isBlank()){
//                    return
//                }
//
//                val wordsEntity = WordsEntity(
//                    wordOriginal = wordOriginal,
//                    wordTranslate = wordTranslate,
//                    note =  note
//                )
//                viewModelScope.launch {
//                    dao.upsertWords(wordsEntity)
//                }
//
//                _state.update { it.copy(
//                    isAddNewWord = false,
//                    wordOriginal = "",
//                    wordTranslate = "",
//                    note = ""
//                ) }
//            }
//
//            is WordsEvent.SetNewWords -> TODO()
//            is WordsEvent.SetNote -> TODO()
//            is WordsEvent.SetTranslateWords -> TODO()
//        }
//    }
//}