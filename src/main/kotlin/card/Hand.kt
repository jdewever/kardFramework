package card

class Hand : SortedCardCollection() {
    fun hasSuit(suit: Suit): Boolean = this.any { it.suit == suit }

    // only hearts and the QS
    fun isAllPoints(): Boolean = this.any { it.getPointsValue() > 0 }
    fun isAllHearts(): Boolean = this.all { it.suit == Suit.HEARTS }
    fun amountOfSuit(suit: Suit): Int = this.count { it.suit == suit }
    fun hasExactOneOfSuit(suit: Suit): Boolean = amountOfSuit(suit) == 1
}