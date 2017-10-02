package com.github.droibit.hello_wear20

import android.os.Bundle
import android.support.wear.widget.drawer.WearableNavigationDrawerView
import android.support.wearable.activity.WearableActivity
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity(),
        MenuItem.OnMenuItemClickListener,
        WearableNavigationDrawerView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topNavigationDrawer.also {
            it.setAdapter(NavigationAdapter(this))
            it.addOnItemSelectedListener(this)
            it.controller.peekDrawer()
        }

        bottomActionDrawer.also {
            it.controller.peekDrawer()
            it.setOnMenuItemClickListener(this)
        }
    }

    // WearableNavigationDrawerView.OnItemSelectedListener

    override fun onItemSelected(pos: Int) {
        Toast.makeText(this, NavigationAdapter.navigationItems[pos].text, Toast.LENGTH_SHORT).show()
    }

    // MenuItem.OnMenuItemClickListener

    override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        return true
    }
}
