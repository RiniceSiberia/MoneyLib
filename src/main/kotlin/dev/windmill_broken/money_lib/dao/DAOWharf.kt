package dev.windmill_broken.money_lib.dao

import dev.windmill_broken.money_lib.dao.database.CoinDBDAO
import dev.windmill_broken.money_lib.dao.database.GemDBDAO
import dev.windmill_broken.money_lib.dao.json.CoinLibrary
import dev.windmill_broken.money_lib.dao.json.GemLibrary
import net.minecraft.world.entity.player.Player
import java.util.UUID

object DAOWharf {

    val coinWharf : CoinDAOWharf =
        if (CoinDBDAO.valid)
            CoinDBDAO
        else
            CoinLibrary

    val gemWharf : GemDAOWharf =
        if (CoinDBDAO.valid)
            GemDBDAO
        else
            GemLibrary


}