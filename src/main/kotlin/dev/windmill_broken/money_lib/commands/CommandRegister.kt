package dev.windmill_broken.money_lib.commands

import dev.windmill_broken.money_lib.MoneyLib.Companion.MOD_ID
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.RegisterCommandsEvent

object CommandRegister {
    fun register(e: RegisterCommandsEvent) {
        CoinCommand.register(e.dispatcher)
        GemCommand.register(e.dispatcher)
    }
}