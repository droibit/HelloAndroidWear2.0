package com.github.droibit.hello_wear20

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.wear.widget.drawer.WearableNavigationDrawerView
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : WearableActivity(),
        MenuItem.OnMenuItemClickListener,
        WearableNavigationDrawerView.OnItemSelectedListener {

    companion object {

        private val TAG = MainActivity::class.java.simpleName
    }

    private val refreshHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topNavigationDrawer.also {
            it.setAdapter(NavigationAdapter(this))
            it.addOnItemSelectedListener(this)
            it.controller.closeDrawer()
        }

        bottomActionDrawer.also {
            it.setOnMenuItemClickListener(this)
        }

        list.also {
            it.adapter = ContentAdapter(this, messages = listOf(
                    "Android ",
                    "Android Android",
                    "Android Android Android ",
                    "Android Android Android Android Android ",
                    "Android Android Android Android Android Android Android Android ",
                    "Android Android Android Android Android Android Android Android Android Android ",
                    "d",
                    "d",
                    "dd",
                    "d")) {
                bottomActionDrawer.controller.openDrawer()
                Log.d(TAG, "click=$it")
            }

            (it.layoutManager as LinearLayoutManager).stackFromEnd = true
        }

        swipeRefresh.also {
            it.setOnRefreshListener {
                refreshHandler.postDelayed({ swipeRefresh.isRefreshing = false },
                        TimeUnit.SECONDS.toMillis(2))
            }
            it.setColorSchemeColors(
                    ContextCompat.getColor(this, android.R.color.holo_blue_bright)
            )
        }
    }

    // WearableNavigationDrawerView.OnItemSelectedListener

    override fun onItemSelected(pos: Int) {
        Toast.makeText(this, NavigationAdapter.navigationItems[pos].text, Toast.LENGTH_SHORT).show()
    }

    // MenuItem.OnMenuItemClickListener

    override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()

        val intent = PhotoActivity.createIntent(this)
        startActivity(intent)

        bottomActionDrawer.controller.closeDrawer()
        return true
    }
}
