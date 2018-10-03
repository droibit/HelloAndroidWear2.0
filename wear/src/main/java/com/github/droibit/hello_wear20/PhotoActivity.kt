package com.github.droibit.hello_wear20

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.PagerSnapHelper
import android.support.wearable.activity.WearableActivity
import kotlinx.android.synthetic.main.activity_main.list

class PhotoActivity : WearableActivity() {

  companion object {

    @JvmStatic
    fun createIntent(context: Context) = Intent(context, PhotoActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_photo)

    list.also {
      it.adapter = PhotoAdapter(
          this, images = intArrayOf(
          // ref. http://www.irasutoya.com
          R.drawable.jiko_kujiku_highheeled,
          R.drawable.numa_lens_hamaru_man,
          R.drawable.sleep_cry_woman
      )
      )
      val snapHelper = PagerSnapHelper()
      snapHelper.attachToRecyclerView(it)
    }
  }
}
