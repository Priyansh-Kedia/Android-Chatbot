package com.kedia.chatbot_app.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.kedia.chatbot_app.R
import com.kedia.chatbot_app.api.Message
import com.kedia.chatbot_app.base.BaseActivity
import com.kedia.chatbot_app.utils.log
import com.kedia.chatbot_app.utils.trimString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }
    private val linearLayoutManager by lazy { LinearLayoutManager(this) }
    private val mainRecyclerViewAdapter by lazy { MainRecyclerViewAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        sendMessage.setOnClickListener {
            makeApiCalls()
        }
        setupObservers()
        setupRecycler()
    }

    private fun setupRecycler() {
        linearLayoutManager.stackFromEnd = true
        chatRecyclerView.apply {
            adapter = mainRecyclerViewAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun setupObservers() {
       mainViewModel.responseMessage.observe(this) {
           log(message = it.response)
           mainRecyclerViewAdapter.addData(Message(it.response))
           chatRecyclerView.smoothScrollToPosition(mainRecyclerViewAdapter.itemCount - 1)
       }

        mainViewModel.errorLiveData.observe(this) {
            log(it)
        }

    }

    private fun makeApiCalls() {
        lifecycleScope.launch {
            mainRecyclerViewAdapter.addData(Message(messageEditText.trimString()))
            mainViewModel.sendMessage(messageEditText.trimString())
            messageEditText.text.clear()
        }
    }
}