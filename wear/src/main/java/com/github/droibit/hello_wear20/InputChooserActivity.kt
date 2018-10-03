package com.github.droibit.hello_wear20

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH
import android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL
import android.speech.RecognizerIntent.EXTRA_PROMPT
import android.speech.RecognizerIntent.EXTRA_RESULTS
import android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
import android.support.wearable.activity.WearableActivity
import android.widget.Toast
import com.github.droibit.hello_wear20.InputKeyboardActivity.Companion.EXTRA_KEYBOARD_INPUT_RESULT
import kotlinx.android.synthetic.main.activity_input_chooser.inputKeyboard
import kotlinx.android.synthetic.main.activity_input_chooser.inputVoice

class InputChooserActivity : WearableActivity() {

  companion object {

    private const val REQUEST_CODE_VOICE_INPUT = 0

    private const val REQUEST_CODE_KEYBOARD_INPUT = 1

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
      startActivityForResult(intent, REQUEST_CODE_VOICE_INPUT)
    }

    inputKeyboard.setOnClickListener {
      val intent = InputKeyboardActivity.createIntent(this)
      startActivityForResult(intent, REQUEST_CODE_KEYBOARD_INPUT)
    }
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)

    if (resultCode != Activity.RESULT_OK || data == null) {
      return
    }

    val text = when (requestCode) {
      REQUEST_CODE_VOICE_INPUT -> data.getStringArrayListExtra(EXTRA_RESULTS)[0]
      REQUEST_CODE_KEYBOARD_INPUT -> data.getStringExtra(EXTRA_KEYBOARD_INPUT_RESULT)
      else -> null
    }

    if (text.isNullOrEmpty()) {
      return
    }
    Toast.makeText(this, text, Toast.LENGTH_SHORT)
        .show()
  }
}
