package com.hank.ccm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuessViewModel : ViewModel() {
    var secret = (1..10).random()
    var secretData = MutableLiveData<Int>()
    var counter = MutableLiveData<Int>()
    var gameStatus = MutableLiveData<GameStatus>()

    init {
        secretData.value = secret
        counter.value = 0
        gameStatus.value = GameStatus.INIT
    }

    fun vmGuess(num: Int): GameStatus? {
        counter.value = counter.value!! + 1
        gameStatus.value = when (num - secret) {
            in 1..Int.MAX_VALUE -> GameStatus.SMALLER
            0 -> GameStatus.BINGO
            else -> GameStatus.BIGGER
        }
        return gameStatus.value
    }

    fun vmReset() {
        secret = (1..10).random()
        secretData.value = secret
        counter.value = 0
        gameStatus.value = GameStatus.INIT
    }

}


enum class GameStatus { INIT, BIGGER, SMALLER, BINGO }
