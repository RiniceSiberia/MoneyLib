package dev.windmill_broken.money_lib.dao.json

import dev.windmill_broken.money_lib.MoneyLib.Companion.LOGGER
import dev.windmill_broken.money_lib.dao.CoinDAOWharf
import dev.windmill_broken.money_lib.dao.DAO
import dev.windmill_broken.money_lib.dao.json.serializer.UUIDSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import net.minecraft.world.entity.player.Player
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Suppress("SEALED_INHERITOR_IN_DIFFERENT_PACKAGE")
object CoinLibrary: ConcurrentHashMap<UUID, Long>(), CoinDAOWharf, DAO.JsonDAO {
    private fun readResolve(): Any = CoinLibrary

    init {
        load()
    }

    override fun select(player: Player): Long = get(player.uuid)?:0

    override fun update(player: Player, amount: Long){
        put(player.uuid, amount)
    }

    override fun plus(player: Player, amount: Long): Long {
        this[player.uuid] = this[player.uuid]?.plus(amount) ?: amount
        return this[player.uuid]!!
    }

    override fun save() {
        val path = JsonUtils.rootFolder.resolve("money.json")
        val json = JsonUtils.jsonConfig.encodeToString(
            MapSerializer(
                UUIDSerializer,
                Long.serializer()
            ),
            this
        )
        try {
            Files.writeString(
            path,
                json,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
            )
        }catch (e : IOException){
            e.printStackTrace()
            LOGGER.error("coin library serialization process error",e)
        }
    }

    override fun load() {
        val path = JsonUtils.rootFolder.resolve("money.json")
        try {
            val jsonStr = Files.readString(path, StandardCharsets.UTF_8)
            val map = JsonUtils.jsonConfig.decodeFromString(MapSerializer(
                UUIDSerializer,
                Long.serializer()
            ),jsonStr)
            this.clear()
            this.putAll(map)
        }catch (e : IOException){
            e.printStackTrace()
            LOGGER.error("coin library deserialization process error",e)
            "{}"
        }
    }


}