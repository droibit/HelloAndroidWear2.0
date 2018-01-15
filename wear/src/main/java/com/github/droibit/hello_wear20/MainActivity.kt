package com.github.droibit.hello_wear20

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.wear.widget.drawer.WearableNavigationDrawerView
import android.support.wearable.activity.WearableActivity
import android.support.wearable.view.AcceptDenyDialogFragment
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.github.droibit.hello_wear20.NavigationAdapter.Companion.navigationItems
import kotlinx.android.synthetic.main.activity_main.bottomActionDrawer
import kotlinx.android.synthetic.main.activity_main.list
import kotlinx.android.synthetic.main.activity_main.swipeRefresh
import kotlinx.android.synthetic.main.activity_main.topNavigationDrawer
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
                    "Android",
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
        when (navigationItems[pos].text) {
            "Input" -> {
                val intent = InputChooserActivity.createIntent(this)
                startActivity(intent)
            }
            "Stack" -> {
                val intent = StackActivity.createIntent(this)
                startActivity(intent)
            }
            "Dialog" -> {
                val dialogFragment = AcceptDenyDialogFragment.Builder()
                        .setTitle("Title")
                        .setMessage("Hello, world.")
                        .setShowPositiveButton(true)
                        .setShowNegativeButton(true)
                        .build()
                dialogFragment.show(fragmentManager, null)
            }
            "Settings" -> {
            }
        }
        Toast.makeText(this, navigationItems[pos].text, Toast.LENGTH_SHORT).show()
    }

    // MenuItem.OnMenuItemClickListener

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_photo -> {
                val intent = PhotoActivity.createIntent(this)
                startActivity(intent)
            }
            else -> Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
        bottomActionDrawer.controller.closeDrawer()
        return true
    }
}
