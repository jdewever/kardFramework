package xyz.jonasdewever

import xyz.jonasdewever.hearts.Hearts
import xyz.jonasdewever.players.HumanPlayer

fun main() {
    val hearts = Hearts(HumanPlayer("Jon"), HumanPlayer("Emile"), HumanPlayer("Rune"), HumanPlayer("Jef"))
    hearts.startGame()
}