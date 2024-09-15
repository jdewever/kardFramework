package card

import java.util.*

open class SortedCardCollection : LinkedList<xyz.jonasdewever.card.ICard>() {

    fun shuffle() = Collections.shuffle(this)
    fun sort() = Collections.sort(this)

    fun calculatePoints(): Int = sumOf { it.getPointsValue() }
    fun getPlusPoints(): Int = filter { it.getPointsValue() > 0 }.sumOf { it.getPointsValue() }
}