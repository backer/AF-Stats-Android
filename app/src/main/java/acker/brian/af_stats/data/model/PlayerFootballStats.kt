package acker.brian.af_stats.data.model

import timber.log.Timber

data class PlayerFootballStats(
    override val playerId: String,
    override val firstName: String,
    override val lastName: String,
    override val sport: Game.Sport = Game.Sport.FOOTBALL,
    var completions: Int = 0,
    var passAttempts: Int = 0,
    var passingYards: Int = 0,
    var passingTd: Int = 0,
    var intThrown: Int = 0,
    var passingPat: Int = 0,
    var receptions: Int = 0,
    var targets: Int = 0,
    var recYards: Int = 0,
    var recTd: Int = 0,
    var recPat: Int = 0,
    var rushAttempts: Int = 0,
    var rushYards: Int = 0,
    var rushTd: Int = 0,
    var rushPat: Int = 0,
    var defSacks: Int = 0,
    var defInt: Int = 0,
    var defTd: Int = 0
) : PlayerStats() {
    var fantasyPoints: Double

    init {
        fantasyPoints = calculateFantasyPoints()
    }

    // calculates total fantasy points based on sub categories
    private fun calculateFantasyPoints(): Double {
        return calculatePassingFantasyPoints() + calculateReceivingFantasyPoints() + calculateRushingFantasyPoints() + calculateDefensiveFantasyPoints()
    }

    // calculates passing fantasy points based on the following weights:
    // 1 point for every 25 passing yards
    // 4 points for every passing touchdown
    // -2 points for every interception thrown
    // 1 point for every pat converted
    private fun calculatePassingFantasyPoints(): Double {
        return passingYards * 0.04 + passingTd * 4 + intThrown * -2 + passingPat * 1
    }

    // calculates receiving fantasy points based on the following weights:
    // 0.5 point for every reception
    // 1 point for every 10 receiving yards
    // 6 points for every receiving touchdown
    // 1 point for every pat converted
    private fun calculateReceivingFantasyPoints(): Double {
        return receptions * 0.5 + recYards * 0.1 + recTd * 6 + recPat * 1
    }

    // calculates rushing fantasy points based on the following weights:
    // 1 point for every 10 rushing yards
    // 6 points for every rushing touchdown
    // 1 point for every pat converted
    private fun calculateRushingFantasyPoints(): Double {
        return rushYards * 0.1 + rushTd * 6 + rushPat * 1
    }

    // calculates defensive fantasy points based on the following weights:
    // 1 point for every sack
    // 3 points for every interception
    // 6 points for every defensive touchdown scored
    private fun calculateDefensiveFantasyPoints(): Double {
        return defSacks * 1.0 + defInt * 3.0 + defTd * 6.0
    }

    // adds the stats from another PlayerFootballStats object to this object's stats
    // will generally be used for tallying the stats from multiple games in a season
    override fun addStats(newStats: PlayerStats) {
        if (newStats !is PlayerFootballStats) {
            // this method should only ever be called with PlayerFootballStats
            Timber.e("PlayerFootballStats.addStats called with incompatible type: %s", newStats.javaClass)
            return
        }

        completions += newStats.completions
        passAttempts += newStats.passAttempts
        passingYards += newStats.passingYards
        passingTd += newStats.passingTd
        intThrown += newStats.intThrown
        passingPat += newStats.passingPat
        receptions += newStats.receptions
        targets += newStats.targets
        recYards += newStats.recYards
        recTd += newStats.recTd
        recPat += newStats.recPat
        rushAttempts += newStats.rushAttempts
        rushYards += newStats.rushYards
        rushTd += newStats.rushTd
        rushPat += newStats.rushPat
        defSacks += newStats.defSacks
        defInt += newStats.defInt
        defTd += newStats.defTd

        fantasyPoints = calculateFantasyPoints()
    }
}