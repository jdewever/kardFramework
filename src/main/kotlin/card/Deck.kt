package card

import xyz.jonasdewever.card.ICard
import kotlin.collections.ArrayList
import kotlin.enums.EnumEntries
import kotlin.reflect.KClass

class Deck<T : ICard>(
    cardType: KClass<T>, ranks: EnumEntries<Rank> = Rank.entries
) : SortedCardCollection() {

    init {
        for (suit in Suit.entries) {
            for (rank in ranks) {
                val card = cardType.constructors.first().call(rank, suit)
                add(card)
            }
        }
        shuffle()
    }

    fun split(playerAmount: Int): ArrayList<Hand> {
        val hands = arrayListOf<Hand>()
        for (i in 0 until playerAmount) hands.add(Hand())

        val cardIterator = iterator()
        var playerDeal: Int = 0
        while (cardIterator.hasNext()) {
            if (playerDeal == playerAmount) playerDeal = 0
            val card = cardIterator.next()
            hands[playerDeal].add(card)
            cardIterator.remove()
            playerDeal++
        }
        return hands
    }
}