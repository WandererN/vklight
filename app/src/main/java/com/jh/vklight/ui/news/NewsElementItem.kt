package com.jh.vklight.ui.news

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import coil.api.load
import coil.transform.CircleCropTransformation
import com.jh.vklight.R
import com.jh.vklight.model.VKGroup
import com.jh.vklight.model.attachment.VKPhotoSize
import com.jh.vklight.model.VKPost
import com.jh.vklight.model.VKProfile
import com.jh.vklight.model.attachment.VKAttachmentPhoto
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.element_news_layout.view.*

class NewsElementItem(
    private val post: VKPost,
    private val profiles: List<VKProfile>,
    private val groups: List<VKGroup>
) : Item() {
    private fun getOptimalSize(width: Int, sizes: List<VKPhotoSize>): VKPhotoSize {
        return sizes.filter {
            it.width <= width
        }.maxBy {
            it.width
        } ?: throw Exception()
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.root.apply {
            viewHolder.itemView.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            post_message_text.text = post.text
            var authorHeading = ""
            var authorPhotoUrl = ""
            if (post.sourceId > 0) {
                profiles.find { it.id == post.sourceId }?.let {
                    authorHeading = "${it.firstName} ${it.lastName}"
                    authorPhotoUrl = it.photo50
                }
            } else {
                groups.find { it.id == -post.sourceId }?.let {
                    authorHeading = it.name
                    authorPhotoUrl = it.photo50
                }
            }
            post_like_count_text.text = post.likes.count.toString()
            post_share_count_text.text = post.reposts.count.toString()
            post_comment_count_text.text = post.comments.count.toString()
            post_view_count_text.text = post.views.count.toString()
            if(post.likes.userLikes == 1) {
                post_like_button.setColorFilter(Color.RED)
            } else {
                post_like_button.colorFilter = null
            }

            post_owner_name.text = authorHeading
            post_owner_icon.load(authorPhotoUrl) {
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_baseline_image_24)
            }
            post_message_photo_holder.removeAllViews()
            val photoCount = post.attachments.size

            attachmentsLoop@ for (attachment in post.attachments) {
                when (attachment) {
                    is VKAttachmentPhoto -> {
                        if (attachment.sizes.isEmpty())
                            continue@attachmentsLoop

                        val size =
                            try {
                                getOptimalSize(
                                    viewHolder.itemView.measuredWidth,
                                    attachment.sizes
                                )
                            } catch (e: Exception) {
                                continue@attachmentsLoop
                            }
                        val img = ImageView(viewHolder.root.context)
                        post_message_photo_holder.addView(img)

                        img.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            800,
                            1.0F / photoCount
                        )
                        img.scaleType = ImageView.ScaleType.CENTER_CROP
                        img.load(size.url) {
                            crossfade(true)
                        }
                    }
                }
            }
            post_message_photo_holder.invalidate()
        }
    }

    override fun getLayout(): Int {
        return R.layout.element_news_layout
    }

}
