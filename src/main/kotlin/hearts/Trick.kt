package xyz.jonasdewever.hearts

import card.SortedCardCollection

class Trick(private val leaderIndex: Int) : SortedCardCollection() {

    fun hasSpadeQueen(): Boolean = contains(Card.SpadeQueen)
    fun hasDiamondJack(): Boolean = contains(Card.DiamondJack)

    fun getTotalPoints(): Int = sumOf { it.getPointsValue() }
    fun hasPlusPoints(): Boolean = any { it.getPointsValue() > 0 }

    fun getEaterIndex(): Int {
        var eater = 0
        for (card in this.subList(1, lastIndex)) {
            if (card.suit != this[0].suit) continue
            if (card.rank > this[eater].rank) eater = indexOf(card)
        }
        return (eater + leaderIndex) % 4
    }
}