package com.jh.vklight.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jh.vklight.Config
import com.jh.vklight.R
import com.jh.vklight.model.VKNewsfeed
import com.jh.vklight.vk.VKNewsFeedRequest
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_news.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()
    private val newsAdapter = GroupAdapter<ViewHolder>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        root.newsfeed_recycler.adapter = newsAdapter

        newsViewModel.newsFeed.observe(viewLifecycleOwner, Observer {
            it.items.forEach { post ->
                newsAdapter.add(NewsElementItem(post, it.profiles, it.groups))
            }
        })
        GlobalScope.launch {
            while (!Config.isLogged) {
                delay(100)
            }
            VK.execute(VKNewsFeedRequest(), object : VKApiCallback<VKNewsfeed> {
                override fun fail(error: Exception) {
                    Log.d("VKNewsFeedRequest", "${error.message}")
                }

                override fun success(result: VKNewsfeed) {
                    newsViewModel.newsFeed.value = result
                    Log.d("VKNewsFeedRequest", result.items.joinToString(","))
                }
            })
        }
        return root
    }

}
