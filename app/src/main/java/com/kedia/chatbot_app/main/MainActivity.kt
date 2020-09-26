package com.kedia.chatbot_app.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.kedia.chatbot_app.R
import com.kedia.chatbot_app.base.BaseActivity
import com.kedia.chatbot_app.utils.log
import com.kedia.chatbot_app.utils.trimString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {

    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        button.setOnClickListener {
            makeApiCalls()
        }
        setupObservers()
    }

    private fun setupObservers() {
       mainViewModel.responseMessage.observe(this) {
           log(message = it.response)
       }

        mainViewModel.errorLiveData.observe(this) {
            log(it)
        }

    }

    private fun makeApiCalls() {
        lifecycleScope.launch {
            mainViewModel.sendMessage(messageText.trimString())
        }
    }
}