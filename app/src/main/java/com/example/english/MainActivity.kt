package com.example.english

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.english.data.room.WordsDb
import com.example.english.data.room.WordsEntity
import com.example.english.presentation.SetupNavGraph
import com.example.english.ui.theme.EnglishTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EnglishTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }

        val db = Room.databaseBuilder(
            applicationContext,
            WordsDb::class.java, "words.db"
        ).build()

//        lifecycleScope.launch { db.dao.getWords().collect(::println) }
//
//        (1..10).forEach {
//            lifecycleScope.launch {
//                db.dao.upsertWords(
//                    WordsEntity(
//                        id = 0,
//                        wordOriginal = "1$it",
//                        wordTranslate = "2$it",
//                        note = "3$it"
//                    )
//                )
//            }
//        }
    }
}
