package acker.brian.af_stats.data.model

import android.util.ArrayMap
import org.json.JSONArray
import org.json.JSONObject

// constants class containing field names for converting data models to/from json
object JsonFields {
    // general field names
    const val PLAYER_ID = "player_id"
    const val FIRST_NAME = "first_name"
    const val LAST_NAME = "last_name"
    const val SPORT = "sport"

    // football stat field names
    const val COMPLETIONS = "completions"
    const val PASS_ATTEMPTS = "pass_attempts"
    const val PASSING_YARDS = "passing_yards"
    const val PASSING_TD = "passing_td"
    const val INT_THROWN = "int_thrown"
    const val PASSING_PAT = "passing_pat"
    const val RECEPTIONS = "receptions"
    const val TARGETS = "targets"
    const val RECEIVING_YARDS = "receiving_yards"
    const val RECEIVING_TD = "receiving_td"
    const val RECEIVING_PAT = "receiving_pat"
    const val RUSH_ATTEMPTS = "rush_attempts"
    const val RUSHING_YARDS = "rushing_yards"
    const val RUSHING_TD = "rushing_td"
    const val RUSHING_PAT = "rushing_pat"
    const val DEF_SACKS = "def_sacks"
    const val DEF_INT = "def_int"
    const val DEF_TD = "def_td"

    // game/season specific field names
    const val GAME_ID = "game_id"
    const val SEASONID = "season_id"
    const val SCORESHEET = "scoresheet"
    const val TEAM_NAME = "team_name"
    const val OPPONENT_NAME = "opponent_name"
    const val TEAM_SCORE = "team_score"
    const val OPPONENT_SCORE = "opponent_score"
    const val RESULT = "result"
    const val YEAR = "year"
    const val SESSION_NUMBER = "sessionNumber"
    const val GAMES = "games"
    const val WINS = "wins"
    const val LOSSES = "losses"
    const val DRAWS = "draws"
    const val TOTAL_POINTS_SCORED = "total_points_scored"
    const val TOTAL_POINTS_ALLOWED = "total_points_allowed"
    const val POINT_DIFFERENTIAL = "point_differential"

    // player specific field names
    const val NUMBERS = "numbers"
    const val SEASONS = "seasons"
    const val ALL_TIME_STATS = "all_time_stats"

    fun statsListToJson(statsList: MutableList<PlayerStats>): JSONArray {
        val jsonArray = JSONArray()

        if (!statsList.isEmpty()) {
            for (stats in statsList) {
                jsonArray.put(stats.toJson())
            }
        }

        return jsonArray
    }

    fun gamesListToJson(games: MutableList<Game>): JSONArray {
        val jsonArray = JSONArray()

        if (!games.isEmpty()) {
            for (game in games) {
                jsonArray.put(game.toJson())
            }
        }

        return jsonArray
    }

    private fun seasonsListToJson(seasons: MutableList<Season>): JSONArray {
        val jsonArray = JSONArray()

        if (!seasons.isEmpty()) {
            for (season in seasons) {
                jsonArray.put(season.toJson())
            }
        }

        return jsonArray
    }

    fun seasonsMapToJson(seasons: MutableMap<Game.Sport, MutableList<Season>> = ArrayMap()): JSONObject {
        val jsonObject = JSONObject()
        for (key in seasons.keys) {
            val seasons = seasons[key]
            if (!seasons.isNullOrEmpty()) {
                jsonObject.put(key.name.toLowerCase(), seasonsListToJson(seasons))
            }
        }

        return jsonObject
    }

    fun allTimeStatsMapToJson(allTimeStats: MutableMap<Game.Sport, PlayerStats>): JSONObject {
        val jsonObject = JSONObject()
        for (key in allTimeStats.keys) {
            val value = allTimeStats[key]
            if (value != null) {
                jsonObject.put(key.name.toLowerCase(), value.toJson())
            }
        }

        return jsonObject
    }

    fun numbersMapToJson(numbers: MutableMap<Game.Sport, Int>): JSONObject {
        val jsonObject = JSONObject()
        for (key in numbers.keys) {
            jsonObject.put(key.name.toLowerCase(), numbers[key])
        }

        return jsonObject
    }
}