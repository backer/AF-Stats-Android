package acker.brian.af_stats.data.model

import org.json.JSONObject

abstract class Play {
    abstract val playNumber: Int
    abstract val gameId: String
    abstract val sport: Game.Sport

    abstract fun toJson(): JSONObject
}