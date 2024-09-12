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
            println("${players[0].name}: ${players[0].points} - ${players[1].name}: ${players[1].points} - ${players[2].name}: ${players[2].points} - ${players[3].name}: ${players[3].points}\n")
        }
        println("\nFinal Scores")
        for (player in players) {
            println("$player: ${player.points}")
        }
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