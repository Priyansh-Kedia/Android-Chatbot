package com.kedia.chatbot_app.api

import retrofit2.http.*

interface ApiClient {


    @FormUrlEncoded
    @POST
    suspend fun sendChat(@Field("question") message: String): Message

}