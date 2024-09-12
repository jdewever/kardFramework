package xyz.jonasdewever.card

import card.Rank
import card.Suit

interface ICard : Comparable<ICard> {
    val rank: Rank
    val suit: Suit
    fun getPointsValue(): Int
    override fun compareTo(other: ICard): Int
    override fun equals(other: Any?): Boolean
    override fun toString(): String
}