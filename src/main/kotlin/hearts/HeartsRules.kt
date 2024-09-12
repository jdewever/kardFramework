package xyz.jonasdewever.hearts

interface HeartsRules {
    val firstRoundQueenAllowed: Boolean
    val firstRoundHeartsAllowed: Boolean
    val firstRoundClubsTwoLeads: Boolean
    val newMoon: Boolean
    val oldMoon: Boolean
    val mixedMoon: Boolean
    val diamondJack: Boolean
    val spadesQueen: Boolean
    val allPassing: Boolean
    val leftPassing: Boolean
    val rightPassing: Boolean
}