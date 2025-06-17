package dev.windmill_broken.money_lib.dao

import net.minecraft.world.entity.player.Player
import java.util.UUID

/**
 * 付费宝石接口
 */
sealed interface GemDAOWharf : DAO{

    fun select(player : Player) : Long

    fun update(player : Player, amount: Long)

    fun plus(player : Player, amount: Long) : Long

    fun minus(player : Player, amount: Long) : Long = plus(player, -amount)

}