package xyz.jonasdewever.players

import xyz.jonasdewever.card.ICard
import xyz.jonasdewever.hearts.Round

class HumanPlayer(name: String) : Player(name) {

    override fun playCard(round: Round): ICard {
        val valid = getValidPlays(round)
        println("$name: ${this.hand}")
        println("Valid plays: $valid")
        val card = valid.random()
        hand.remove(card)
        return card
    }
}