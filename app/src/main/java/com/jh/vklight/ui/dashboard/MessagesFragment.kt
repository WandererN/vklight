package com.jh.vklight.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jh.vklight.R

class MessagesFragment : Fragment() {

    private val messagesViewModel: MessagesViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        messagesViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
}
