package com.jh.vklight.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VKPostComments(
    val count: Int,
    @SerialName("can_post")
    val canPost: Int)
