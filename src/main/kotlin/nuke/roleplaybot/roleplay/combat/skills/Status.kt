package nuke.roleplaybot.roleplay.combat.skills

data class Status(val type: StatusType, val argument: Int = 0) {

    enum class StatusType {
        SLOW, STUN, POISON, BURNING, CHILLED, CONFUSION, SPELL_LOCK, LEACHED

        // marks
        // ...
    }

}