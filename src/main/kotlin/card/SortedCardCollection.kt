package card

import java.util.Collections
import java.util.LinkedList

open class SortedCardCollection : LinkedList<xyz.jonasdewever.card.ICard>() {

    fun shuffle() = Collections.shuffle(this)
    fun sort() = Collections.sort(this)

    fun calculatePoints(): Int = sumOf { it.getPointsValue() }
}