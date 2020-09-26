package com.kedia.chatbot_app.main

import com.kedia.chatbot_app.api.ApiClient
import com.kedia.chatbot_app.api.Message
import com.kedia.chatbot_app.utils.NetworkResult
import com.kedia.chatbot_app.utils.log
import com.kedia.chatbot_app.utils.safeApiCall
import javax.inject.Inject

class MainRepo @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun sendMessage(message: String): NetworkResult<Message>? {
        var networkResult: NetworkResult<Message>? = null

        safeApiCall({ apiClient.sendChat(message) },
            {
                networkResult = it },
            {
                log("called repo " + networkResult.toString())
                networkResult = it }
        )

        return networkResult!!
    }

}