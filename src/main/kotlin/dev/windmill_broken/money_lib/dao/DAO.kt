package dev.windmill_broken.money_lib.dao

import dev.windmill_broken.money_lib.dao.database.DatabaseUtils

interface DAO {

    val valid : Boolean
        get() = true

    interface DBDAO : DAO{
        override val valid: Boolean
            get() = DatabaseUtils.DATABASE_VALID
    }

    interface JsonDAO : DAO{

        fun save()

        fun load()
    }
}