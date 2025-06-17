package dev.windmill_broken.money_lib

import dev.windmill_broken.money_lib.MoneyLib.Companion.MOD_ID
import dev.windmill_broken.money_lib.dao.json.CoinLibrary
import dev.windmill_broken.money_lib.dao.json.GemLibrary
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.server.ServerStoppingEvent
import net.neoforged.neoforge.event.tick.ServerTickEvent

object OtherEvents {

    private var tickCounter = 0

    private const val SAVE_INTERVAL = 30 * 60 * 20

    fun onServerTicket(event : ServerTickEvent.Post){
        tickCounter++

        if (tickCounter >= SAVE_INTERVAL) {
            tickCounter = 0
            CoinLibrary.save()
            GemLibrary.save()
        }
    }

    fun onServerStopping(event: ServerStoppingEvent){
        CoinLibrary.save()
        GemLibrary.save()
    }
}