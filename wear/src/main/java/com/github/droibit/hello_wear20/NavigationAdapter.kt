package com.github.droibit.hello_wear20

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.wear.widget.drawer.WearableNavigationDrawerView

data class NavigationItem(val text: String, @DrawableRes val iconRes: Int)

class NavigationAdapter(private val context: Context) :
        WearableNavigationDrawerView.WearableNavigationDrawerAdapter() {

    companion object {

        val navigationItems = listOf(
                NavigationItem(text = "Input", iconRes = R.drawable.ic_keyboard),
                NavigationItem(text = "Stack", iconRes = R.drawable.ic_android),
                NavigationItem(text = "Archive", iconRes = R.drawable.ic_archive),
                NavigationItem(text = "Build", iconRes = R.drawable.ic_build),
                NavigationItem(text = "AudioTrack", iconRes = R.drawable.ic_audiotrack)
        )
    }

    override fun getItemText(pos: Int) = navigationItems[pos].text

    override fun getItemDrawable(pos: Int) = ContextCompat.getDrawable(context, navigationItems[pos].iconRes)!!

    override fun getCount() = navigationItems.size
}