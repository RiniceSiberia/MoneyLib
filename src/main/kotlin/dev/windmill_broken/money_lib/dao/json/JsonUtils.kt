package dev.windmill_broken.money_lib.dao.json

import dev.windmill_broken.money_lib.Config
import dev.windmill_broken.money_lib.MoneyLib
import dev.windmill_broken.money_lib.utils.LateInitObjects
import kotlinx.serialization.json.Json
import net.minecraft.world.level.storage.LevelResource
import net.neoforged.fml.loading.FMLPaths
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists

object JsonUtils {

    val jsonConfig = Json{}

    val globalRootFolder : Path
        get() {
            val gameDir = FMLPaths.GAMEDIR.get()
            val myGlobalJsonDir = gameDir.resolve("antinomy_global_data")
            if (!myGlobalJsonDir.exists()){
                Files.createDirectories(myGlobalJsonDir)
            }
            return myGlobalJsonDir
        }

    val worldRootFolder: Path
        get() = LateInitObjects.SERVER.getWorldPath(LevelResource.ROOT).toAbsolutePath()

    val rootFolder : Path
        get() = if (Config.ENABLE_JSON_MODEL_GLOBAL_MONEY){
            globalRootFolder
        }else{
            worldRootFolder
        }
}