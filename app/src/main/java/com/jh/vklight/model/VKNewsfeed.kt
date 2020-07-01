package com.jh.vklight.model

import kotlinx.serialization.Serializable

@Serializable
data class VKNewsfeed(
    val items: List<VKPost>,
    val profiles: List<VKProfile>,
    val groups: List<VKGroup>
)