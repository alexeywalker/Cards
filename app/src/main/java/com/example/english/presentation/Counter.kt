package com.example.english.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable

fun Counter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    var oldCount by remember {
        mutableIntStateOf(count)
    }
    SideEffect {
        oldCount = count
    }
    Row(modifier = modifier) {
        val countToString = count.toString()
        val oldCountToString = oldCount.toString()
        for (i in countToString.indices) {
            val oldCharacter = oldCountToString.getOrNull(i)
            val newCharacter = countToString[i]
            val character = if (oldCharacter == newCharacter) {
                oldCountToString[i]
            } else {
                countToString[i]
            }
            AnimatedContent(targetState = character,
                transitionSpec = {
                    slideInVertically { it } togetherWith slideOutVertically { -it }
                }, label = ""
            ) { character ->
                Text(
                    text = character.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}