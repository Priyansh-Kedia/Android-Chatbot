package com.kedia.chatbot_app.main

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kedia.chatbot_app.R
import com.kedia.chatbot_app.api.Message
import com.kedia.chatbot_app.base.BaseRecyclerAdapter
import com.kedia.chatbot_app.utils.log

class MainRecyclerViewAdapter(private val list: MutableList<Message>): BaseRecyclerAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MESSAGE.SENDER.type) {
            ViewHolder(getLayout(R.layout.item_chat_sender, parent))
        } else {
            ViewHolder(getLayout(R.layout.item_chat_receiver, parent))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0)
            MESSAGE.SENDER.type
        else
            MESSAGE.RECEIVER.type

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder)
            holder.bind(list[position])
    }

    fun addData(message: Message) {
        this.list.add(message)
        notifyItemInserted(list.size -1)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val chatMessage: TextView = itemView.findViewById(R.id.chatMessage)

        fun bind(item: Message) {
            chatMessage.text = item.response
        }
    }

    enum class MESSAGE(val type: Int) {
        SENDER(1),
        RECEIVER(2)
    }
}