package xyz.jonasdewever.hearts

import card.Deck
import xyz.jonasdewever.players.Player

class Round(
    private val players: List<Player>, val roundNumber: Int, private val deck: Deck<Card> = Deck(Card::class)
) {
    var pointsBroken = false
    private lateinit var lastTrick: Trick
    lateinit var currentTrick: Trick
    private lateinit var startingPlayer: Player

    init {
        val hands = deck.split(players.size)
        for (index in 0..<players.size) {
            players[index].hand = hands[index]
            players[index].hand.sort()
        }
    }

    fun isRoundOver() = players.all { it.hand.isEmpty() }
    fun setStartingPlayer(player: Player) {
        startingPlayer = player
    }

    fun playTrick() {
        currentTrick = Trick(players.indexOf(startingPlayer))
        for (i in 0..<players.size) {
            val player = players[(players.indexOf(startingPlayer) + i) % 4]
            val card = player.playCard(this)
            currentTrick.add(card)
        }

        val eater = players[currentTrick.getEaterIndex()]
        val pointsToTake = currentTrick.calculatePoints()
        eater.points += pointsToTake
//        println("Trick: $currentTrick - Eater: $eater - Points: $pointsToTake")
//        println("${players[0].name}: ${players[0].points} - ${players[1].name}: ${players[1].points} - ${players[2].name}: ${players[2].points} - ${players[3].name}: ${players[3].points}\n")
        setStartingPlayer(players[currentTrick.getEaterIndex()])
    }

    fun passCards() {
        return
    }
}