package xyz.jonasdewever.hearts

import game.Game
import xyz.jonasdewever.players.Player

class Hearts(p1: Player, p2: Player, p3: Player, p4: Player) : Game() {
    val reportsPerRound: MutableList<RoundReport> = mutableListOf()
    lateinit var currentRound: Round

    init {
        players.addAll(listOf(p1, p2, p3, p4))
    }

    // A round of hearts, a deck is split between 4 players, 13 cards each, playing 13 tricks
    override fun doRound(roundNumber: Int) {
        currentRound = Round(this, players, roundNumber)

        currentRound.passCards()
        players.forEach { it.hand.sort() }
        // re-set starting player after passing
        currentRound.setStartingPlayer(players.first { it.hasCard(Card.ClubTwo) })

        // Round loop, goes 13 times, aka until everyone is out of cards
        while (!currentRound.isRoundOver()) {
            currentRound.playTrick()
        }
        
        moonShooting(currentRound)

        // round is over, do points thingy
        for (player in players) {
            player.totalPoints += player.roundPoints
            player.roundPoints = 0
        }

        // create round report
        val roundReport = currentRound.createReport()
        reportsPerRound.add(roundReport)


        players.forEach { it.cardsTaken.clear() }
    }

    // todo option for new, old or mixed (mixed atm)
    private fun moonShooting(round: Round) {
        // only runs when there is a shooter
        round.whoShotMoon()?.let { shooter ->
            // simulate end points, everyone besides shooter takes 26, the jack of course gets added
            val simPoints = players.zip(players.map {
                it.totalPoints + (if (it == shooter) 0 else 26) + if (round.jackTaker?.equals(it) == true) -10 else 0
            })
            val simMax = simPoints.maxOf { it.second }
            val simWinner = if (simMax >= 100) simPoints.minBy { it.second }.first else null
            val multipleUltimateLosers = simPoints.count { it.second == simMax } > 1

            // when the game does not end (no one >= 100)
            // or there are multiple ultimate losers (equal max score >= 100) (this continues play)
            // or simulated winner would be the shooter
            // do old moon
            if (simPoints.all { it.second < 100 } || multipleUltimateLosers || simWinner == shooter) doOldMoon(
                shooter, round.jackTaker
            )
            // when the game would end with
            // one distinct loser
            // and the shooter not winning
            // do new moon
            else doNewMoon(shooter, round.jackTaker)
        }
    }

    // shooter 0, rest +26, jack
    private fun doOldMoon(shooter: Player, jackTaker: Player?) {
        for (player in players) {
            player.roundPoints = (if (player == shooter) 0 else 26) + if (jackTaker?.equals(player) == true) -10 else 0
        }
    }

    // shooter -26, rest 0, jack
    private fun doNewMoon(shooter: Player, jackTaker: Player?) {
        for (player in players) {
            player.roundPoints = (if (player == shooter) -26 else 0) + if (jackTaker?.equals(player) == true) -10 else 0
        }
    }

    override fun isGameOver(): Boolean {
        // if no one >= 100, no loser (yet)
        if (players.none { it.totalPoints >= 100 }) return false
        // get the highest score
        val highest = players.maxOf { it.totalPoints }
        // if there are multiple players with the highest score, continue for another round
        return players.filter { it.totalPoints == highest }.size == 1
    }
}