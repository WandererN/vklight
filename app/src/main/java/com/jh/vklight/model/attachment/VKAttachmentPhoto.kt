package com.jh.vklight.model.attachment

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Polymorphic
@Serializable
open class VKAttachmentPhoto(
    val id: Long,
    @SerialName("owner_id")
    val ownerId: Long,
//    @SerialName("user_id")
//    val userId: Long,
    val sizes: List<VKPhotoSize>
) : VKAttachment()