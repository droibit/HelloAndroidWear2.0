package com.github.droibit.hello_wear20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class InputChooserActivity : WearableActivity() {

    companion object {

        @JvmStatic
        fun createIntent(context: Context) = Intent(context, InputChooserActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_chooser)


    }
}
