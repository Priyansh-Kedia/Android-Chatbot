package com.kedia.chatbot_app.utils

import android.util.Log
import android.widget.TextView

suspend fun <T> safeApiCall(
    call: suspend() -> T,
    onSuccess: (NetworkResult.Success<T>) -> Unit,
    onFailure: (NetworkResult.Error) -> Unit
) {
    runCatching {
        val response = call()
        onSuccess.invoke(NetworkResult.Success(response))
    }.onFailure {
        it.printStackTrace()
        onFailure.invoke(NetworkResult.Error(it.message))
    }
}

fun log(message: String?) {
    message?.let { Log.d("ChatBotApp", it) }
}

fun TextView.trimString(): String {
    return this.text.toString().trim()
}