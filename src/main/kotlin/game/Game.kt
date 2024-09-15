package game

import xyz.jonasdewever.players.Player

abstract class Game {
    val players: MutableList<Player> = mutableListOf()
    var startingPlayer: Int = 0

    fun startGame() {
        var currentRound = 0
        while (isGameOver().not()) {
            currentRound++
            doRound(currentRound)
        }
    }

    abstract fun isGameOver(): Boolean
    abstract fun doRound(roundNumber: Int)
}