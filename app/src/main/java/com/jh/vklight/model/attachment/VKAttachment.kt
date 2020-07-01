package com.jh.vklight.model.attachment

import kotlinx.serialization.*
import kotlinx.serialization.json.JsonInput
import kotlinx.serialization.json.JsonObject

open class VKAttachment {
    @Serializer(forClass = VKAttachment::class)
    companion object {
        override fun deserialize(decoder: Decoder): VKAttachment {
            val input = decoder as? JsonInput
                ?: throw SerializationException("Expected JsonInput for ${decoder::class}")
            val jsonObject = input.decodeJson() as? JsonObject
                ?: throw SerializationException("Expected JsonObject for ${input.decodeJson()::class}")
            return when (jsonObject.getPrimitive("type").content) {
                "photo" -> decoder.json.parse(VKAttachmentPhoto.serializer(), jsonObject.getObject("photo").toString())
                //TODO add another
                else -> VKAttachment()
            }

        }
    }
}

