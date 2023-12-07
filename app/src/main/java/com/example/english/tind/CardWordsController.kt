package com.example.english.tind

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


/**
 * To control the CardWords.
 * Used to swipe card programmatically
 */
@Composable
fun rememberCardWordsController(): CardWordsController {
    return remember { CardWordsControllerImpl() }
}

interface CardWordsController {
    /**
     * Points to the top card's [CardController]
     */
    var currentCardController: CardController?
    fun swipeRight()
    fun swipeLeft()
}

class CardWordsControllerImpl : CardWordsController {
    override var currentCardController: CardController? = null

    override fun swipeRight() {
        currentCardController?.swipeRight()
    }

    override fun swipeLeft() {
        currentCardController?.swipeLeft()
    }
}