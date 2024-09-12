package xyz.jonasdewever.players

import card.Suit
import xyz.jonasdewever.card.ICard
import xyz.jonasdewever.hearts.Card
import xyz.jonasdewever.hearts.Round

class ComputerPlayer(name: String) : Player(name) {
    override fun playCard(round: Round): ICard {
        val valid = getValidPlays(round)    // get all valid cards to play at the moment
        if (valid.size == 1) {
            // if we only have one card, play it, duh
            hand.remove(valid[0])
            return valid[0]
        }

        val leadingSuit: Suit? = round.currentTrick.getOrNull(0)?.suit

        val card = if (leadingSuit == null) {
            // we're leading, not first round
            if (valid.isAllHearts()) valid[0] // lowest hearts
            else valid.filter { it.suit != Suit.HEARTS }.minBy { it.rank }  // lowest non hearts
        } else {
            // we're following
            if (!(valid.hasSuit(leadingSuit))) {
                if (valid.contains(Card.SpadeQueen)) Card.SpadeQueen  // queen at earliest opportunity
                else if (valid.hasSuit(Suit.HEARTS)) valid.filter { it.suit == Suit.HEARTS }.last() // highest hearts
                else valid.filter { it.suit != Suit.HEARTS }.maxBy { it.rank }    // highest non-hearts
            } else {
                valid.filter { it.suit == leadingSuit }.first()  // lowest of leading suit
            }
        }

        hand.remove(card)
        return card
    }

}