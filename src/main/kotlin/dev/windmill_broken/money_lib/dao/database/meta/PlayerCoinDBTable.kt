package dev.windmill_broken.money_lib.dao.database.meta

import org.jetbrains.exposed.dao.id.UUIDTable

object PlayerCoinDBTable : UUIDTable("player_coin","p_id") {
    val pName = varchar("p_name", 50)
    val coin = long(name = "coin")
}