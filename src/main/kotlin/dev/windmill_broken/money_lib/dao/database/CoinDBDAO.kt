package dev.windmill_broken.money_lib.dao.database

import dev.windmill_broken.money_lib.dao.CoinDAOWharf
import dev.windmill_broken.money_lib.dao.DAO
import dev.windmill_broken.money_lib.dao.database.dto.PlayerCoinDBEntity
import dev.windmill_broken.money_lib.dao.database.meta.PlayerCoinDBTable
import net.minecraft.world.entity.player.Player
import org.jetbrains.exposed.sql.transactions.transaction

@Suppress("SEALED_INHERITOR_IN_DIFFERENT_PACKAGE")
object CoinDBDAO : CoinDAOWharf, DAO.DBDAO{

    init {
        if (valid){
            transaction(db = DatabaseUtils.DATABASE) {
                MigrationUtils.statementsRequiredForDatabaseMigration(PlayerCoinDBTable)
            }
        }
    }


    override fun select(player: Player): Long {
        return transaction(db = DatabaseUtils.DATABASE) {
            PlayerCoinDBEntity.findById(player.uuid)?.coin?:let {
                PlayerCoinDBEntity.new(player.uuid){
                    pName = player.scoreboardName
                    coin = 0L
                }.coin
            }
        }
    }

    override fun update(player: Player, amount: Long) {
        transaction(db = DatabaseUtils.DATABASE) {
            val pc = PlayerCoinDBEntity.findById(player.uuid)
            if (pc != null){
                pc.coin = amount
            }else{
                PlayerCoinDBEntity.new(player.uuid) {
                    pName = player.scoreboardName
                    coin = amount
                }
            }
        }
    }

    override fun plus(player: Player, amount: Long): Long {
        return transaction(db = DatabaseUtils.DATABASE) {
            val pc = PlayerCoinDBEntity.findById(player.uuid)
            return@transaction if (pc != null){
                pc.coin = pc.coin + amount
                pc.coin
            }else{
                PlayerCoinDBEntity.new(player.uuid) {
                    pName = player.scoreboardName
                    coin = amount
                }.coin
            }
        }
    }

}