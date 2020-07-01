package com.jh.vklight.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VKPostLikes(
    val count: Int,
    @SerialName("user_likes")
    val userLikes: Int,
    @SerialName("can_like")
    val canLike: Int,
    @SerialName("can_publish")
    val canPublish: Int
)
