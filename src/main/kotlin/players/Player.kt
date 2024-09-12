package xyz.jonasdewever.players

import card.Hand
import card.Suit
import xyz.jonasdewever.card.ICard
import xyz.jonasdewever.hearts.Card
import xyz.jonasdewever.hearts.Round

abstract class Player(val name: String) {
    lateinit var hand: Hand
    var points = 0 // todo find better place for this

    abstract fun playCard(round: Round): ICard

    // TODO clean this up
    fun getValidPlays(round: Round): List<ICard> {
        val validPlays = mutableListOf<ICard>()
        val leadSuit = round.currentTrick.getOrNull(0)?.suit

        if (hand.size == 1) validPlays.addAll(hand)

        // if we have clubs two we can only play that
        else if (hand.contains(Card.ClubTwo)) validPlays.add(Card.ClubTwo)
        else if (leadSuit == null) {
            // we're leading and don't have clubs two = not first round, spade queen allowed
            validPlays.addAll(hand)
            if (!round.pointsBroken && !hand.isAllHearts()) validPlays.removeAll { it.suit == Suit.HEARTS }
        } else {
            // we're following
            // if we're following a spade, it's not the first round thus the queen is allowed
            if (hand.hasSuit(leadSuit)) validPlays.addAll(hand.filter { it.suit == leadSuit })
            else {
                // we're not following
                if (round.roundNumber == 1 && hand.isAllPoints()) {
                    // if first round, we only have hearts AND spadeQueen, set spadeQueen as the only valid play
                    if (hand.contains(Card.SpadeQueen)) validPlays.add(Card.SpadeQueen)
                    // if we don't have the queen, that means all we have is hearts, set them all as valid play
                    else validPlays.addAll(hand)
                } else if (hand.isAllHearts() && !round.pointsBroken) {
                    validPlays.addAll(hand)
                } else validPlays.addAll(hand)
            }
        }

        return validPlays
    }

    override fun toString(): String {
        return name
    }
}