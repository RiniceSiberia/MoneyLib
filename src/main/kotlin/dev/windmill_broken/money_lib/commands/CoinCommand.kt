package dev.windmill_broken.money_lib.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.LongArgumentType
import dev.windmill_broken.money_lib.dao.DAOWharf
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

object CoinCommand{

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("money")
                .then(Commands.literal("coin")
                    .then(Commands.literal("get")
                        .executes { ctx ->
                            val player = ctx.source.player ?: return@executes 0
                            val balance = DAOWharf.coinWharf.select(player)
                            sendCoinMessage(player, "commands.money.coin.get.success", balance)
                            1
                        })
                    .then(withAmountArg("set", "commands.money.coin.set.success")
                    { player, amount ->
                        DAOWharf.coinWharf.update(player, amount)
                        amount
                    })
                    .then(withAmountArg("plus", "commands.money.coin.plus.success")
                    { player, amount ->
                        DAOWharf.coinWharf.plus(player, amount)
                    })
                    .then(withAmountArg("minus", "commands.money.coin.minus.success")
                    { player, amount ->
                        DAOWharf.coinWharf.minus(player, amount)
                    })
                )
        )
    }

    private fun sendCoinMessage(player: ServerPlayer, translationKey: String, amount: Long) {
        player.sendSystemMessage(Component.translatable(translationKey, amount))
    }


    private fun withAmountArg(
        name: String,
        translationKey: String,
        op: (ServerPlayer, Long) -> Long
    ) = Commands.literal(name)
        .then(
            Commands.argument("amount", LongArgumentType.longArg(0L))
                .executes { ctx ->
                    val player = ctx.source.player ?: return@executes 0
                    val amount = LongArgumentType.getLong(ctx, "amount")
                    val result = op(player, amount)
                    sendCoinMessage(player, translationKey, result)
                    1
                }
        )
}