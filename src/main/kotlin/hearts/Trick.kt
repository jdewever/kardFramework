package xyz.jonasdewever.hearts

import card.SortedCardCollection

class Trick(val leaderIndex: Int) : SortedCardCollection() {

    val hasSpadeQueen: Boolean = contains(Card.SpadeQueen)
    val hasDiamondJack: Boolean = contains(Card.DiamondJack)

    fun getEaterIndex(): Int {
        var eater = 0
        for (card in this.subList(1, lastIndex)) {
            if (card.suit != this[0].suit) continue
            if (card.rank > this[eater].rank) eater = indexOf(card)
        }
        return (eater + leaderIndex) % 4
    }
}