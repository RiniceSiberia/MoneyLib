package dev.windmill_broken.money_lib.utils

import dev.windmill_broken.money_lib.MoneyLib.Companion.MOD_ID
import dev.windmill_broken.money_lib.dao.DAOWharf
import net.minecraft.core.RegistryAccess
import net.minecraft.server.MinecraftServer
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.event.server.ServerStartingEvent
import kotlin.collections.first

object LateInitObjects {

    lateinit var dao : DAOWharf

    lateinit var SERVER : MinecraftServer

    val serverId
        get() = SERVER

    lateinit var REGISTRY_ACCESS : RegistryAccess

    fun onServerStarting(event: ServerStartingEvent){
        SERVER = event.server
        REGISTRY_ACCESS = SERVER.allLevels.first().registryAccess()
    }
}