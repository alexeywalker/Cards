package com.example.english.presentation

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.english.R
import com.example.english.data.datastore.CounterManager
import com.example.english.data.room.WordsEntity
import com.example.english.flip.CardFlip
import com.example.english.flip.rememberFlipController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
//    state: WordsState,
//    wordsEvent: (WordsEvent) -> Unit
) {

    var checked by remember { mutableStateOf(true) }
    val icon: (@Composable () -> Unit)? = if (checked) {
        {
            Icon(
                painter = painterResource(id = R.drawable.brightness),
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }

    // Count
    var count by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val contextCount = LocalContext.current
    val counterManager = remember { CounterManager(contextCount) }
    LaunchedEffect(Unit) {
        count = counterManager.getCounter()
    }

    // Share
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "http://play.google.com/store/apps/details?id=com.google.android.apps.maps"
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val contextShare = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Counter(count = count, style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { contextShare.startActivity(shareIntent) }) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Share"
                        )
                    }
                    IconButton(onClick = {
//                        wordsEvent(WordsEvent.ShowAddNewWordsScreen)


                        navController.navigate(route = Screen.AddNewWord.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "AddNewWord"
                        )
                    }
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        thumbContent = icon
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val items = remember { mutableStateListOf(*('A'..'Z').toList().toTypedArray()) }
            val flipController = rememberFlipController()

            val generateBoxModifier: () -> Modifier = {
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
//                    .background(
//                        brush = Brush.horizontalGradient(
//                            listOf(
//                                randomColor(),
//                                randomColor()
//                            )
//                        )
//                    )
            }

            CardFlip(
                items = items,
                cardFlipController = flipController,
                onItemRemoved = { item, direction ->
                    println("Item removed: $item -> $direction")
                    items.remove(item)
                },
                cardModifier = generateBoxModifier,
                onEmpty = {
                    println("End reached")
                },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                front = { item ->
                    FrontText(item = item)
                },
                back = { item ->
                    ReverseText(item = "${item.code - 65 + 1}")
                })

//                Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
//                        wordsEvent(
//                            WordsEvent.DeleteWords(
//                                wordsEntity = WordsEntity(
//                                    wordOriginal = "",
//                                    wordTranslate = "",
//                                    note = ""
//                                )
//                            )
//                        )
                        flipController.swipeLeft()

                        scope.launch {
                            counterManager.incrementCounter()
                            count = counterManager.getCounter()
                        }
                    }) {
                        Icon(
                            Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        flipController.flip()
                    }) {
                        Icon(
                            Icons.Outlined.Info,
                            contentDescription = "Info",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = {
                        flipController.swipeRight()
                    }) {
                        Icon(
                            Icons.Outlined.Refresh,
                            contentDescription = "Refresh",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FrontText(modifier: Modifier = Modifier, item: Char) {
    AutoResizeText(
        modifier = modifier,
        text = "$item",
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun ReverseText(modifier: Modifier = Modifier, item: String) {
    AutoResizeText(
        modifier = modifier,
        text = item,
        color = Color.White
    )
}


