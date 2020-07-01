package com.jh.vklight.model.attachment

import kotlinx.serialization.Serializable
import org.json.JSONObject

@Serializable
data class VKPhotoSize(
    val height: Int,
    val width: Int,
    val url: String,
    val type: String
)