package acker.brian.af_stats.data.model

import android.util.ArrayMap
import timber.log.Timber

class Player(
    val firstName: String,
    val lastName: String,
    val playerId: String,
    val numbers: MutableMap<Game.Sport, Int> = ArrayMap(),
    val seasons: MutableMap<Game.Sport, MutableList<Season>> = ArrayMap(),
    val allTimeStats: MutableMap<Game.Sport, PlayerStats> = ArrayMap()
) {

    // calculates all time stats based on stats from each season
    private fun calculateAllTimeFromSeasons() {
        // reset values before iteration
        allTimeStats.clear()

        // iterate through each available sport and update the corresponding stats
        enumValues<Game.Sport>().forEach {
            if (!seasons[it].isNullOrEmpty()) {
                // seasons are available for this sport, tally stats
                sumSeasonsStats(it, seasons[it]!!)
            }
        }
    }

    private fun sumSeasonsStats(sport: Game.Sport, seasons: MutableList<Season>) {
        if (seasons.isEmpty()) {
            return
        }

        val firstSeasonPlayerIndex = seasons[0].findPlayerStatsById(playerId)
        if (firstSeasonPlayerIndex < 0) {
            // this should never happen, seasons should only be in this list if player is on season scoresheet
            Timber.e("Attempted to sum season stats for season without player on roster")
            return
        }

        val firstPlayerStats = seasons[0].scoreSheet[firstSeasonPlayerIndex]
        if (!allTimeStats.containsKey(sport)) {
            allTimeStats[sport] = firstPlayerStats
        }

        if (seasons.size > 1) {
            // since we have already added the first season, iterate through the rest and sum them together
            for (i in 1 until seasons.size - 1) {
                val playerStatsIndex = seasons[i].findPlayerStatsById(playerId)
                if (playerStatsIndex >= 0) {
                    allTimeStats[sport]?.addStats(seasons[i].scoreSheet[playerStatsIndex])
                }
            }
        }
    }

    companion object {
        // creates an id for a new player based off of first name, last name, and initial number
        fun createPlayerId(firstName: String, lastName: String, number: Int): String {
            return firstName[0].plus(lastName).plus(number)
        }
    }
}