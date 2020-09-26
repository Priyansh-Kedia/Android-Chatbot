package com.kedia.chatbot_app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.kedia.chatbot_app.api.Message
import com.kedia.chatbot_app.base.BaseViewModel
import com.kedia.chatbot_app.utils.NetworkResult
import com.kedia.chatbot_app.utils.log
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepo: MainRepo,
    private val gson: Gson
): BaseViewModel() {


    private val _responseMessage = MutableLiveData<Message>()
    val responseMessage: LiveData<Message>
        get() = _responseMessage

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData


    suspend fun sendMessage(message: String) {
        val response = mainRepo.sendMessage(message)


        when (response) {
            is NetworkResult.Success -> {
                val data = response.data
                _responseMessage.value = data
            }
            is NetworkResult.Error -> {
                val data = response.exception
                _errorLiveData.value = data
            }
        }
    }

}