package xyz.jonasdewever.hearts

import card.Rank
import card.Suit
import xyz.jonasdewever.card.ICard

class Card(override val rank: Rank, override val suit: Suit) : ICard {
    val shortString: Boolean = true

    override fun getPointsValue(): Int {
        return when {
            suit == Suit.HEARTS -> 1
            suit == Suit.SPADES && rank == Rank.QUEEN -> 13
            suit == Suit.DIAMONDS && rank == Rank.JACK -> -10
            else -> 0
        }
    }

    override fun compareTo(other: ICard): Int {
        val suitCompare = suit.compareTo(other.suit)
        return if (suitCompare != 0) suitCompare else rank.compareTo(other.rank)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Card) return false
        return rank == other.rank && suit == other.suit
    }

    override fun hashCode(): Int {
        var result = rank.hashCode()
        result = 31 * result + suit.hashCode()
        return result
    }

    override fun toString(): String {
        return if (shortString) "${suit.symbol}${rank.symbol}" else "${rank.name} of ${suit.name}"
    }


    companion object {
        val SpadeQueen = Card(Rank.QUEEN, Suit.SPADES)
        val DiamondJack = Card(Rank.JACK, Suit.DIAMONDS)
        val ClubTwo = Card(Rank.TWO, Suit.CLUBS)
    }


}