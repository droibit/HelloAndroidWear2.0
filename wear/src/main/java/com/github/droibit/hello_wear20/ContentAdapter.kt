package com.github.droibit.hello_wear20

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.message

class ContentAdapter(
  context: Context,
  private val messages: List<String>,
  private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

  private val inflater = LayoutInflater.from(context)

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(text: String) {
      itemView.message.text = text
    }
  }

  override fun getItemCount() = messages.size

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    holder.bind(messages[position])
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val itemView = inflater.inflate(R.layout.list_item, parent, false)
    return ViewHolder(itemView).apply {
      itemView.setOnClickListener { clickListener(adapterPosition) }
    }
  }
}