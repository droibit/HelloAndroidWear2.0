package com.github.droibit.hello_wear20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent.*
import android.support.wearable.activity.WearableActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_input_chooser.*

class InputChooserActivity : WearableActivity() {

    companion object {

        private const val SPEECH_REQUEST_CODE = 0

        @JvmStatic
        fun createIntent(context: Context) = Intent(context, InputChooserActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_chooser)

        inputVoice.setOnClickListener {
            val intent = Intent(ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(EXTRA_LANGUAGE_MODEL, LANGUAGE_MODEL_FREE_FORM)

                putExtra(EXTRA_PROMPT, "Create")
            }
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK
                && data != null) {
            val spokenText = data.getStringArrayListExtra(EXTRA_RESULTS)[0]
            if (spokenText.isEmpty()) {
                return
            }

            Toast.makeText(this, spokenText, Toast.LENGTH_SHORT).show()
        }
    }
}
