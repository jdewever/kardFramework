package game

import xyz.jonasdewever.players.Player

abstract class Game {
    val players: MutableList<Player> = mutableListOf()
    var startingPlayer: Int = 0

    fun startGame() {
        var currentRound = 0
        /*while (isGameOver().not()) {
            currentRound++
            doRound(currentRound)
        }*/
        currentRound++
        doRound(currentRound)
    }

    fun printStartingHands() {
        for (player in players) {
            println(
                player.name + ": " + player.hand.toString() + if (startingPlayer == players.indexOf(
                        player
                    )
                ) " (Starts)" else ""
            )
        }
    }

    abstract fun isGameOver(): Boolean
    abstract fun doRound(roundNumber: Int)
}