package dev.windmill_broken.money_lib.dao.json.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

/**
 *      使用IDEA编写
 * @Author: DUELIST
 * @Time:  2023-07-27-17:47
 * @Message: UUID类的序列化器
 **/
object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("uuid", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: UUID) {
        val composite = encoder.beginStructure(descriptor)
        composite.encodeStringElement(descriptor,0,value.toString())
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): UUID {
        val composite = decoder.beginStructure(descriptor)
        val uuid : String = composite.decodeStringElement(descriptor,0)
        composite.endStructure(descriptor)
        return UUID.fromString(uuid)
    }
}