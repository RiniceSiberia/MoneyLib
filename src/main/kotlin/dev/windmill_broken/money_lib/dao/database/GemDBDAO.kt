package dev.windmill_broken.money_lib.dao.database

import dev.windmill_broken.money_lib.dao.DAO
import dev.windmill_broken.money_lib.dao.GemDAOWharf
import dev.windmill_broken.money_lib.dao.database.dto.PlayerGemDBEntity
import dev.windmill_broken.money_lib.dao.database.meta.PlayerGemDBTable
import net.minecraft.world.entity.player.Player
import org.jetbrains.exposed.sql.transactions.transaction


@Suppress("SEALED_INHERITOR_IN_DIFFERENT_PACKAGE")
object GemDBDAO : GemDAOWharf, DAO.DBDAO{

    init {
        if (CoinDBDAO.valid){
            transaction(db = DatabaseUtils.DATABASE) {
                MigrationUtils.statementsRequiredForDatabaseMigration(PlayerGemDBTable)
            }
        }
    }
    override fun select(player: Player): Long {
        return transaction(db = DatabaseUtils.DATABASE) {
            PlayerGemDBEntity.findById(player.uuid)?.gem?:let {
                PlayerGemDBEntity.new(player.uuid){
                    pName = player.scoreboardName
                    gem = 0L
                }.gem
            }
        }
    }

    override fun update(player: Player, amount: Long) {
        transaction(db = DatabaseUtils.DATABASE) {
            val pc = PlayerGemDBEntity.findById(player.uuid)
            if (pc != null){
                pc.gem = amount
            }else{
                PlayerGemDBEntity.new(player.uuid) {
                    pName = player.scoreboardName
                    gem = amount
                }
            }
        }
    }

    override fun plus(player: Player, amount: Long): Long {
        return transaction(db = DatabaseUtils.DATABASE) {
            val pc = PlayerGemDBEntity.findById(player.uuid)
            return@transaction if (pc != null){
                pc.gem = pc.gem + amount
                pc.gem
            }else{
                PlayerGemDBEntity.new(player.uuid) {
                    pName = player.scoreboardName
                    gem = amount
                }.gem
            }
        }
    }

}