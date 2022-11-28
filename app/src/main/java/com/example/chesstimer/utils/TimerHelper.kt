package com.example.chesstimer.utils

import android.os.Handler
import android.widget.TextView


class TimerHelper(private val textView: TextView) : Runnable {
    private val handler: Handler = Handler()

    private var startTime: Long = 0
    var elapsedTime: Long = 0

    override fun run() {
        val millis = System.currentTimeMillis() - startTime
        var seconds = (millis / 1000).toInt()
        val minutes = seconds / 60
        seconds %= 60
        textView.text = String.format("%d:%02d", minutes, seconds)
        if (elapsedTime == -1L) {
            handler.postDelayed(this, 500)
        }
    }

    fun start() {
        startTime = System.currentTimeMillis()
        elapsedTime = -1
        handler.post(this)
    }

    fun stop() {
        elapsedTime = System.currentTimeMillis() - startTime
        handler.removeCallbacks(this)
    }
}