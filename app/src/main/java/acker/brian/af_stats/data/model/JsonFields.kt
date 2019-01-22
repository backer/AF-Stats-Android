package acker.brian.af_stats.data.model

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
}