package com.jh.vklight.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VKGroup(
    val id: Long,
    val name: String,
    @SerialName("photo_50")
    val photo50: String
)