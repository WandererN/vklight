package com.jh.vklight.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jh.vklight.model.VKNewsfeed

class NewsViewModel : ViewModel() {

    private val _text = MutableLiveData<VKNewsfeed>()
    val newsFeed: MutableLiveData<VKNewsfeed> = _text
}