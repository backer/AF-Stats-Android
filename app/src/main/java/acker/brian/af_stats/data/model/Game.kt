package acker.brian.af_stats.data.model

import org.json.JSONObject

class Game(
        val sport: Sport,
        val gameId: String,
        val scoreSheet: MutableList<PlayerStats> = ArrayList(),
        // play by play is optional
        val playByPlay: MutableList<Play> = ArrayList(),
        val seasonId: String,
        val teamName: String,
        val opponentName: String = DEFAULT_OPPONENT,
        var teamScore: Int = 0,
        var opponentScore: Int = 0,
        var result: Result = Result.DRAW
) {

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(JsonFields.SPORT, sport)
        jsonObject.put(JsonFields.GAME_ID, gameId)
        jsonObject.put(JsonFields.SCORESHEET, JsonFields.statsListToJson(scoreSheet))
        jsonObject.put(JsonFields.SEASONID, seasonId)
        jsonObject.put(JsonFields.TEAM_NAME, teamName)
        jsonObject.put(JsonFields.OPPONENT_NAME, opponentName)
        jsonObject.put(JsonFields.TEAM_SCORE, teamScore)
        jsonObject.put(JsonFields.OPPONENT_SCORE, opponentScore)
        jsonObject.put(JsonFields.RESULT, result)
        if (!playByPlay.isNullOrEmpty()) {
            jsonObject.put(JsonFields.PLAY_BY_PLAY, JsonFields.playListToJson(playByPlay))
        }

        return jsonObject
    }

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