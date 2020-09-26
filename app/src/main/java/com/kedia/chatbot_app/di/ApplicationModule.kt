package com.kedia.chatbot_app.di

import android.app.Application
import android.content.Context
import com.kedia.chatbot_app.ChatBotApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApp(): Application {
        return ChatBotApp.instance
    }
    @Provides
    @Singleton
    fun getContext(): Context = ChatBotApp.instance

}