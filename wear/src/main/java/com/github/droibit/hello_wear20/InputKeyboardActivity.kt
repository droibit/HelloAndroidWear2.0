package com.github.droibit.hello_wear20

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.view.inputmethod.InputMethodManager.SHOW_FORCED
import kotlinx.android.synthetic.main.activity_input_keyboard.editText

class InputKeyboardActivity : WearableActivity() {

  companion object {

    const val EXTRA_KEYBOARD_INPUT_RESULT = "EXTRA_KEYBORD_INPUT_RESULT"

    @JvmStatic
    fun createIntent(context: Context) = Intent(context, InputKeyboardActivity::class.java)
  }

  private val keyboard by lazy { getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_input_keyboard)

    editText.setOnEditorActionListener { v, actionId, _ ->
      var handled = false
      if (actionId == EditorInfo.IME_ACTION_GO) {
        keyboard.hideSoftInputFromWindow(v.windowToken, 0)
        val data = Intent().apply { putExtra(EXTRA_KEYBOARD_INPUT_RESULT, v.text.toString()) }
        setResult(Activity.RESULT_OK, data)
        finish()
        handled = true
      }
      handled
    }

    // FIXME: keyboard dose not show when activity start
    keyboard.toggleSoftInput(SHOW_FORCED, HIDE_IMPLICIT_ONLY)
//        keyboard.showSoftInput(container, 0)
  }

  override fun onKeyDown(
    keyCode: Int,
    event: KeyEvent
  ): Boolean {
    return super.onKeyDown(keyCode, event)
  }
}
