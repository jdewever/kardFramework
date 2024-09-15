package xyz.jonasdewever

import xyz.jonasdewever.hearts.Hearts
import xyz.jonasdewever.players.ComputerPlayer

fun main() {
    val hearts = Hearts(ComputerPlayer("Jon"), ComputerPlayer("Emile"), ComputerPlayer("Rune"), ComputerPlayer("Jef"))
    hearts.startGame()

    println("Round Reports")
    hearts.reportsPerRound.forEach { println(it) }

    println("End Score")
    for (player in hearts.players) {
        println("${player.name}: ${player.totalPoints}")
    }
}