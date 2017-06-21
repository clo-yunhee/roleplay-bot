package org.kud.roleplay.command.roleplay.characters

import org.jetbrains.exposed.sql.transactions.transaction
import org.kud.roleplay.command.meta.CommandContext
import org.kud.roleplay.command.meta.command.Command
import org.kud.roleplay.database.Character

object CharaCreateCommand : Command() {

    override fun onInvoke(context: CommandContext) {
        val guildId = context.event.guild.idLong
        val userId = context.event.author.idLong

        if (context.tokenizer.hasMore) {
            val name = context.tokenizer.tail()

            // TODO: sanity check for name

            transaction {
                if (!Character.exists(guildId, userId, name)) {
                    Character.new {
                        this.guildId = guildId
                        this.userId = userId
                        this.name = name
                    }
                    context.reply("your character \"$name\" has been created!")
                } else {
                    context.replyFail("you already have a character with that name.")
                }
            }
        } else {
            context.replyFail("you haven't specified a character name.")
        }
    }

}
