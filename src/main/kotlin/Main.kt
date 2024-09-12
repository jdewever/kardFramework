package xyz.jonasdewever

import xyz.jonasdewever.hearts.Hearts
import xyz.jonasdewever.players.ComputerPlayer

fun main() {
    val hearts = Hearts(ComputerPlayer("Jon"), ComputerPlayer("Emile"), ComputerPlayer("Rune"), ComputerPlayer("Jef"))
    hearts.startGame()
}