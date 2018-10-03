package com.github.droibit.hello_wear20

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_photo.view.image

class PhotoAdapter(
  context: Context,
  @DrawableRes private val images: IntArray
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(@DrawableRes imageResId: Int) {
      itemView.image.setImageResource(imageResId)
    }
  }

  private val inflater = LayoutInflater.from(context)

  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) {
    holder.bind(images[position])
  }

  override fun getItemCount() = images.size

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val itemView = inflater.inflate(R.layout.list_item_photo, parent, false)
    return ViewHolder(itemView)
  }
}