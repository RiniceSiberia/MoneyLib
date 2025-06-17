package dev.windmill_broken.money_lib.dao.database

import dev.windmill_broken.money_lib.Config
import org.jetbrains.exposed.sql.Database

object DatabaseUtils {
    private val database = lazy {
        Database.Companion.connect(
            url = Config.DB_PATH + Config.DB_NAME + Config.DB_EXTRA_OPTIONS,
            driver = Config.DB_DRIVER,
            user = Config.DB_USERNAME,
            password = Config.DB_PWD
        )
    }
    val DATABASE : Database?
        get() = try {
            database.value
        }catch (e : Throwable){
            e.printStackTrace()
            null
        }
}