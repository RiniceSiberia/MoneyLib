package dev.windmill_broken.money_lib.dao.database.meta

import org.jetbrains.exposed.dao.id.UUIDTable

object PlayerGemDBTable : UUIDTable("player_gem","p_id") {
    val pName = varchar("p_name", 50)
    val gem = long(name = "gem")
}