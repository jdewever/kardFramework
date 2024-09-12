package card

enum class Suit(val symbol: String, val color: String) {
    CLUBS("\u2663", "#000"),
    DIAMONDS("\u2666", "#f00"),
    SPADES("\u2660", "#000"),
    HEARTS("\u2665", "#f00");

    private val useSymbol = true

    override fun toString(): String {
        return if (useSymbol) symbol else name
    }
}