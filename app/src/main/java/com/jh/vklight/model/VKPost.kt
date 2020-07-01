package com.jh.vklight.model

import com.jh.vklight.model.attachment.VKAttachment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VKPost(
    val date: Long,
    @SerialName("source_id")
    val sourceId: Long,
    @SerialName("post_id")
    val postId: Long,
    val text: String,
    val attachments: List<VKAttachment> = emptyList(),
    val comments: VKPostComments,
    val likes: VKPostLikes,
    val reposts: VKPostRepost,
    val views: VKPostViews

)