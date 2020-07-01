package com.jh.vklight.vk

import com.jh.vklight.model.VKNewsfeed
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.requests.VKRequest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.json.JSONObject

class VKNewsFeedRequest : VKRequest<VKNewsfeed> {
    constructor() : super("newsfeed.get") {
        addParam("filters", "post,profiles")
    }

    override fun parse(r: JSONObject): VKNewsfeed {
        return Json(JsonConfiguration(ignoreUnknownKeys = true, useArrayPolymorphism = true)).parse(
            VKNewsfeed.serializer(),
            r.getJSONObject("response").toString()
        )
    }
}