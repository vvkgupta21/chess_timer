package com.example.chesstimer.utils

import android.os.Handler
import android.widget.TextView
import java.util.*

object UtilityMethods {
    private var seconds: Int = 0
    fun runTimer(textView: TextView, running: Boolean) {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time: String =
                    java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, secs)
                textView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}