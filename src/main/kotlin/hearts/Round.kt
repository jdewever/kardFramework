package xyz.jonasdewever.hearts

import card.Deck
import xyz.jonasdewever.players.Player

class Round(
    private val game: Hearts,
    private val players: List<Player>,
    val roundNumber: Int,
    private val deck: Deck<Card> = Deck(Card::class)
) {
    var pointsBroken = false
    private val allTricks: MutableList<Trick> = mutableListOf()
    lateinit var currentTrick: Trick
    private var startingPlayer: Player = players[0]
    var queenTaker: Player? = null
    var jackTaker: Player? = null

    init {
        val hands = deck.split(players.size)
        for (player in players) {
            player.roundPoints = 0
            player.hand = hands.removeFirst()
            player.hand.sort()
            if (player.hasCard(Card.ClubTwo)) startingPlayer = player
        }
    }

    fun isRoundOver() = players.all { it.hand.isEmpty() }
    fun setStartingPlayer(player: Player) {
        startingPlayer = player
    }

    fun playTrick() {
        currentTrick = Trick(players.indexOf(startingPlayer))
        for (i in players.indices) {
            val player = players[(players.indexOf(startingPlayer) + i) % 4]
            val card = player.playCard(this)
            currentTrick.add(card)
        }

        if (currentTrick.hasPlusPoints()) pointsBroken = true

        val eater = players[currentTrick.getEaterIndex()]
        val pointsToTake = currentTrick.calculatePoints()
        eater.roundPoints += pointsToTake

        eater.cardsTaken.addAll(currentTrick)

        if (currentTrick.hasDiamondJack()) jackTaker = eater
        if (currentTrick.hasSpadeQueen()) queenTaker = eater

        // Set starting player for next trick
        setStartingPlayer(players[currentTrick.getEaterIndex()])
        allTricks.add(currentTrick)
    }

    fun whoShotMoon(): Player? = players.singleOrNull { it.cardsTaken.getPlusPoints() == 26 }

    fun createReport(): RoundReport {
        val playersWithPoints = players.zip(players.map { it.totalPoints })
        val moonShooter = whoShotMoon()

        val prevPoints = game.reportsPerRound.lastOrNull()?.playersWithPoints
        val playersWithPointDelta =
            if (prevPoints != null) players.zip(players.map { player -> player.totalPoints - prevPoints.single { it.first == player }.second }) else playersWithPoints


        return RoundReport(
            roundNumber, playersWithPoints, playersWithPointDelta, queenTaker!!, jackTaker!!, moonShooter
        )
    }

    fun passCards() {
        return
    }
}