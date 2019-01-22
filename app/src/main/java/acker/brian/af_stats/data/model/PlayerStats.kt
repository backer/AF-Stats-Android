package acker.brian.af_stats.data.model

abstract class PlayerStats {
    abstract val playerId: String
    abstract val firstName: String
    abstract val lastName: String
    abstract val sport: Game.Sport

    abstract fun addStats(newStats: PlayerStats)
}