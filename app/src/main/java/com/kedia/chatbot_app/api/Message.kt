package com.kedia.chatbot_app.api

import com.google.gson.annotations.SerializedName

data class Message (

    @SerializedName("message")
    val response: String
)