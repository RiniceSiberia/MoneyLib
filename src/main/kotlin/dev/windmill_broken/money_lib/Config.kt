package dev.windmill_broken.money_lib

import dev.windmill_broken.money_lib.MoneyLib.Companion.MOD_FOLDER_ID
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.config.ModConfigEvent
import net.neoforged.neoforge.common.ModConfigSpec

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = MoneyLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object Config {

    private val Builder: ModConfigSpec.Builder = ModConfigSpec.Builder()

    private val dbPath: ModConfigSpec.ConfigValue<String> = Builder
        .define("db_path", "jdbc:mysql://localhost:3306/")
    private val dbDriver: ModConfigSpec.ConfigValue<String> = Builder
        .define("db_driver", "com.mysql.cj.jdbc.Driver")
    private val dbUsername: ModConfigSpec.ConfigValue<String> = Builder
        .define("db_username", "yourusername")
    private val dbPwd: ModConfigSpec.ConfigValue<String> = Builder
        .define("db_pwd", "yourpassword")
    private val dbName: ModConfigSpec.ConfigValue<String> = Builder
        .define("db_name", "testdb")
    private val dbExtraOptions: ModConfigSpec.ConfigValue<String> = Builder
            .define("db_extra_options", "?useSSL=false&serverTimezone=UTC")

    private val enableJsonModelGlobalMoney : ModConfigSpec.ConfigValue<Boolean> = Builder
        .comment("The default binding of the player's currency storage table in the database is linked to save files. \n" +
                "If this configuration starts up, all save files using the same database link will share a currency storage table.")
        .define("json_model_global_money", false)




    val SPEC: ModConfigSpec? = Builder.build()

    lateinit var DB_PATH : String
        private set
    lateinit var DB_DRIVER : String
        private set
    lateinit var DB_USERNAME : String
        private set
    lateinit var DB_PWD : String
        private set
    lateinit var DB_NAME : String
        private set
    lateinit var DB_EXTRA_OPTIONS : String
        private set
    var ENABLE_JSON_MODEL_GLOBAL_MONEY : Boolean = false
        private set


    fun register(container : ModContainer){
        container.registerConfig(ModConfig.Type.SERVER, SPEC,"${MOD_FOLDER_ID}/money_settings_server.cfg")
    }

    @SubscribeEvent
    @JvmStatic
    fun onLoad(event: ModConfigEvent) {
        DB_PATH = dbPath.get()
        DB_DRIVER = dbDriver.get()
        DB_USERNAME = dbUsername.get()
        DB_PWD = dbPwd.get()
        DB_NAME = dbName.get()
        DB_EXTRA_OPTIONS = dbExtraOptions.get()
        ENABLE_JSON_MODEL_GLOBAL_MONEY = enableJsonModelGlobalMoney.get()
    }
}