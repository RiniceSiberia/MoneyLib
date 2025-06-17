package dev.windmill_broken.money_lib

import com.mojang.logging.LogUtils
import dev.windmill_broken.money_lib.commands.CommandRegister
import dev.windmill_broken.money_lib.utils.LateInitObjects
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import org.slf4j.Logger

@Mod(MoneyLib.MOD_ID)
class MoneyLib(modEventBus: IEventBus, modContainer: ModContainer) {
    init {
        Config.register(modContainer)

        with(NeoForge.EVENT_BUS){
            addListener(LateInitObjects::onServerStarting)
            addListener(CommandRegister::register)
        }
    }

    companion object {
        const val MOD_FOLDER_ID = "windmill_broken_mods"

        const val MOD_ID: String = "money_lib"

        val LOGGER: Logger = LogUtils.getLogger()
    }
}