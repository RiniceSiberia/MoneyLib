package dev.windmill_broken.money_lib.dao.database.dto

import dev.windmill_broken.money_lib.dao.database.meta.PlayerCoinDBTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class PlayerCoinDBEntity(
    id : EntityID<UUID>
) : UUIDEntity(id){
    companion object : UUIDEntityClass<PlayerCoinDBEntity>(PlayerCoinDBTable)

    var pName by PlayerCoinDBTable.pName
    var coin by PlayerCoinDBTable.coin
}