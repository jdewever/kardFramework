package xyz.jonasdewever.hearts

import xyz.jonasdewever.players.Player

data class RoundReport(
    val roundNumber: Int,
    val playersWithPoints: List<Pair<Player, Int>>,
    val playersWithPointDelta: List<Pair<Player, Int>>,
    val queenTaker: Player,
    val jackTaker: Player,
    val moonShooter: Player?,
)
