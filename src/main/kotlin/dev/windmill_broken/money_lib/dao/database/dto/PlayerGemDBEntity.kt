package dev.windmill_broken.money_lib.dao.database.dto

import dev.windmill_broken.money_lib.dao.database.meta.PlayerGemDBTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class PlayerGemDBEntity(
    id : EntityID<UUID>
) : UUIDEntity(id){
    companion object : UUIDEntityClass<PlayerGemDBEntity>(PlayerGemDBTable)

    var pName by PlayerGemDBTable.pName
    var gem by PlayerGemDBTable.gem
}