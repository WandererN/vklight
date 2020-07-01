package com.jh.vklight.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VKPostRepost(
    val count: Int,
    @SerialName("user_reposted")
    val userReposted: Int
)