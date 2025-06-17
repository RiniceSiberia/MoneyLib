package dev.windmill_broken.money_lib.dao

import dev.windmill_broken.money_lib.dao.database.DatabaseUtils

sealed interface DAO {

    val valid : Boolean
        get() = true

    interface DBDAO : DAO{
        override val valid: Boolean
            get() = DatabaseUtils.DATABASE != null
    }

    interface JsonDAO : DAO{

        fun save()

        fun load()
    }
}