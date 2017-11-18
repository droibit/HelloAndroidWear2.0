package com.github.droibit.hello_wear20

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.wear.widget.SwipeDismissFrameLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_stack_content.*

class StackActivity : FragmentActivity() {

    class ContentFragment1 : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
                inflater.inflate(R.layout.fragment_stack_content, container, false)

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            button.also {
                it.text = "Content1"
                it.setOnClickListener {
                    fragmentManager.beginTransaction()
                            .add(R.id.content, ContentFragment2())
                            .addToBackStack(null)
                            .commit()
                }
            }
            requireNotNull(view).setBackgroundColor(Color.DKGRAY)
        }
    }

    class ContentFragment2 : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return SwipeDismissFrameLayout(context).also {
                val inflatedView = inflater.inflate(R.layout.fragment_stack_content, container, false)
                it.addView(inflatedView)
                it.addCallback(object : SwipeDismissFrameLayout.Callback() {
                    override fun onDismissed(layout: SwipeDismissFrameLayout) {
                        layout.visibility = View.INVISIBLE
                        fragmentManager.popBackStack()
                    }
                })
            }
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            button.also {
                it.text = "Content2"
                it.setOnClickListener {
                    fragmentManager.beginTransaction()
                            .add(R.id.content, ContentFragment1())
                            .addToBackStack(null)
                            .commit()
                }
            }
            requireNotNull(view).setBackgroundColor(Color.BLACK)
        }
    }

    companion object {

        @JvmStatic
        fun createIntent(context: Context) = Intent(context, StackActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content, ContentFragment1())
                    .commitNow()
        }
    }
}
