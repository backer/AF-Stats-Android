package acker.brian.af_stats.data.model

import org.json.JSONObject

class FootballPlay(
        override val playNumber: Int,
        override val gameId: String,
        override val sport: Game.Sport,
        var playType: PlayType,
        // the first player listed in a play action, required for every play
        // quarterback for passing plays, runner for rushing plays, defender for defensive plays
        // defaults to opponent for opponent scores
        var player: String = "opponent",
        // receiver for passing plays, null for other play types
        var receiver: String?,
        // yardage gained/lossed on the play, required for PASS and RUN, optional for sack, 0 otherwise
        var playYardage: Int = 0,
        var playResult: PlayResult = PlayResult.GAIN
) : Play() {
    override fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(JsonFields.PLAY_NUMBER, playNumber)
        jsonObject.put(JsonFields.GAME_ID, gameId)
        jsonObject.put(JsonFields.SPORT, sport)
        jsonObject.put(JsonFields.PLAY_TYPE, playType.value)
        jsonObject.put(JsonFields.PLAYER, player)
        if (receiver != null) {
            jsonObject.put(JsonFields.RECEIVER, receiver)
        }
        jsonObject.put(JsonFields.PLAY_YARDAGE, playYardage)
        jsonObject.put(JsonFields.PLAY_RESULT, playResult.value)

        return jsonObject
    }

    override fun toString(): String {
        val playString =
                when (playType) {
                    PlayType.PASS ->
                        playType.value.plus(" from $player to $receiver for a $playYardage yard ${playResult.value}")
                    PlayType.RUN ->
                        playType.value.plus(" by $player for a $playYardage yard ${playResult.value}")
                    PlayType.INT_THROWN ->
                        playType.value.plus(" by $player")
                    PlayType.SACK -> {
                        playType.value.plus(" by $player").plus(if (playYardage > 0) " for $playYardage yards" else "")
                    }
                    PlayType.DEF_INT ->
                        playType.value.plus(" by $player").plus(
                                if (playResult.equals(PlayResult.TOUCHDOWN)) " for a " + "touchdown" else "")
                    else -> playType.value
                }

        return playString
    }

    enum class PlayType(val value: String) {
        PASS("pass"),
        RUN("run"),
        INT_THROWN("int thrown"),
        SACK("sack"),
        DEF_INT("def int"),
        OPPONENT_TOUCHDOWN("opponent touchdown"),
        OPPONENT_PAT1("opponent 1pt conv"),
        OPPONENT_PAT2("opponent 2pt conv"),
        OPPONENTPAT3("opponent 3pt conv"),
    }

    enum class PlayResult(val value: String) {
        GAIN("gain"),
        TOUCHDOWN("touchdown"),
        FIRST_DOWN("first down"),
        PAT1("1pt conv"),
        PAT2("2pt conv"),
        PAT3("3pt conv"),
        LOSS("loss")
    }
}