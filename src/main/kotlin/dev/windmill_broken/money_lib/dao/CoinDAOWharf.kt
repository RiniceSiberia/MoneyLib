package dev.windmill_broken.money_lib.dao

import net.minecraft.world.entity.player.Player

/**
 * 免费金币接口
 */
sealed interface CoinDAOWharf : DAO{

    fun select(player : Player) : Long

    fun update(player : Player, amount: Long)

    fun plus(player : Player, amount: Long) : Long

    fun minus(player : Player, amount: Long) : Long = plus(player, -amount)
}