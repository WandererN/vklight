package com.jh.vklight.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VKProfile(
    val id: Long = 0,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("photo_50")
    val photo50: String
)

