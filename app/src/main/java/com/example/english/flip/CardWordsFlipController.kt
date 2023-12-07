package com.example.english.flip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * To control the CardWordsFlip.
 * Used to swipe card and rotate programmatically
 */
@Composable
fun rememberFlipController(): CardWordsFlipController {
    return remember { CardWordsFlipControllerImpl() }
}

interface CardWordsFlipController {
    /**
     * Points to the top card's [FlipCardController]
     */
    var currentCardController: FlipCardController?
    fun swipeRight()
    fun swipeLeft()
    fun flip()
}

class CardWordsFlipControllerImpl : CardWordsFlipController {
    override var currentCardController: FlipCardController? = null

    override fun swipeRight() {
        currentCardController?.swipeRight()
    }

    override fun swipeLeft() {
        currentCardController?.swipeLeft()
    }

    override fun flip() {
        currentCardController?.setCardFlipState()
    }
}