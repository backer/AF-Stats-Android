package acker.brian.af_stats.data.model

import org.json.JSONObject

class Season(
    val teamName: String,
    val seasonId: String,
    val year: String,
    val sessionNumber: Int,
    val sport: Game.Sport,
    var games: MutableList<Game> = ArrayList(),
    var scoreSheet: MutableList<PlayerStats> = ArrayList(),
    var winCount: Int = 0,
    var lossCount: Int = 0,
    var drawCount: Int = 0,
    var totalPointsScored: Int = 0,
    var totalPointsAllowed: Int = 0,
    var pointDifferential: Int = 0
) {
    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(JsonFields.YEAR, year)
        jsonObject.put(JsonFields.SESSION_NUMBER, sessionNumber)
        jsonObject.put(JsonFields.SPORT, sport)
        jsonObject.put(JsonFields.GAMES, JsonFields.gamesListToJson(games))
        jsonObject.put(JsonFields.SCORESHEET, JsonFields.statsListToJson(scoreSheet))
        jsonObject.put(JsonFields.WINS, winCount)
        jsonObject.put(JsonFields.LOSSES, lossCount)
        jsonObject.put(JsonFields.DRAWS, drawCount)
        jsonObject.put(JsonFields.SEASONID, seasonId)
        jsonObject.put(JsonFields.TEAM_NAME, teamName)
        jsonObject.put(JsonFields.TOTAL_POINTS_SCORED, totalPointsScored)
        jsonObject.put(JsonFields.TOTAL_POINTS_ALLOWED, totalPointsAllowed)
        jsonObject.put(JsonFields.POINT_DIFFERENTIAL, pointDifferential)

        return jsonObject
    }

    // calculates all relevant values based on the games list
    fun tallyValuesFromGames() {
        if (games.isEmpty()) {
            return;
        }

        // reset values
        winCount = 0
        lossCount = 0
        drawCount = 0
        totalPointsScored = 0
        totalPointsAllowed = 0
        pointDifferential = 0
        scoreSheet.clear()

        // iterate through games and adjust stats
        for (game in games) {
            updateRecord(game)
            updateScoreSheet(game)
        }
    }

    // add game to season and update totals
    fun addGame(game: Game) {
        games.add(game)
        updateRecord(game)
        updateScoreSheet(game)
    }

    private fun updateRecord(game: Game) {
        when (game.result) {
            Game.Result.WIN -> winCount++
            Game.Result.LOSS -> lossCount++
            Game.Result.DRAW -> drawCount++
        }

        totalPointsScored += game.teamScore
        totalPointsAllowed += game.opponentScore
        pointDifferential = totalPointsScored - totalPointsAllowed
    }

    private fun updateScoreSheet(game: Game) {
        if (game.scoreSheet.isEmpty()) {
            return
        }

        for (playerStats in game.scoreSheet) {
            val index = findPlayerStatsById(playerStats.playerId)
            if (index < 0) {
                // if player stats do not exist in current score sheet, add the game stats object to season scoresheet
                scoreSheet.add(playerStats)
            } else {
                // otherwise, add all of the playerStats from the game to the corresponding player's season stats
                scoreSheet[index].addStats(playerStats)
            }
        }
    }

    // returns the index of the playerstats found in the score sheet, or -1 if it doesn't exist
    fun findPlayerStatsById(id: String): Int {
        if (!scoreSheet.isEmpty()) {
            for (index in 0 until scoreSheet.size - 1) {
                if (id.equals(scoreSheet.get(index).playerId)) {
                    return index
                }
            }
        }

        return -1
    }
}