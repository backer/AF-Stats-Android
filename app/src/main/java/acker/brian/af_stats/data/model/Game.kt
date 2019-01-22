package acker.brian.af_stats.data.model

class Game(
    val sport: Sport,
    val gameId: String,
    val scoreSheet: MutableList<PlayerStats> = ArrayList(),
    val seasonId: String,
    val teamName: String,
    val opponentName: String = DEFAULT_OPPONENT,
    var teamScore: Int = 0,
    var opponentScore: Int = 0,
    var result: Result = Result.DRAW
) {

    enum class Sport {
        FOOTBALL,
        SOCCER
    }

    enum class Result {
        WIN,
        LOSS,
        DRAW
    }

    companion object {
        private const val DEFAULT_OPPONENT = "Opponent"
    }
}