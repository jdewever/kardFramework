package xyz.jonasdewever.hearts

import game.Game
import xyz.jonasdewever.players.Player

class Hearts(p1: Player, p2: Player, p3: Player, p4: Player) : Game() {
    lateinit var currentRound: Round

    init {
        players.addAll(listOf(p1, p2, p3, p4))
    }

    // A round of hearts, a deck is split between 4 players, 13 cards each, playing 13 tricks
    override fun doRound(roundNumber: Int) {
        currentRound = Round(players, roundNumber)

        currentRound.passCards()
        players.forEach { it.hand.sort() }

        currentRound.setStartingPlayer(players.first { it.hand.contains(Card.ClubTwo) })

        // Round loop, goes 13 times, aka until everyone is out of cards
        while (!currentRound.isRoundOver()) {
            currentRound.playTrick()
        }


    }

    override fun isGameOver(): Boolean {
        // if no one >= 100, no loser (yet)
        if (players.none { it.points >= 100 }) return false
        // get the highest score
        val highest = players.maxOf { it.points }
        // if there are multiple players with the highest score, continue for another round
        return players.filter { it.points == highest }.size == 1
    }
}