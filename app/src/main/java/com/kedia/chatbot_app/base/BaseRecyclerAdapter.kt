package com.kedia.chatbot_app.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    protected fun getContext(): Context {
        return context
    }

    protected fun getLayout(@LayoutRes layoutId: Int, viewGroup: ViewGroup): View {
        return LayoutInflater.from(context).inflate(layoutId,viewGroup,false)
    }
}