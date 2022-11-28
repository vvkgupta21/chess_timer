package com.example.chesstimer

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.chesstimer.databinding.ActivityStopWatchBinding
import java.util.*

class ChessMainActivity : Activity() {
    lateinit var binding : ActivityStopWatchBinding
    private var seconds = 0
    private var running = false
    private var wasRunning = false

    private var secondMemberSeconds = 0
    private var secondMemberRunning = false
    private var secondMemberWasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stop_watch)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")

            secondMemberSeconds = savedInstanceState.getInt("secondMemberSeconds")
            secondMemberRunning = savedInstanceState.getBoolean("secondMemberRunning")
            secondMemberWasRunning = savedInstanceState.getBoolean("secondMemberWasRunning")
        }
        binding.imgTwo.setBackgroundResource(R.drawable.ic_pause_icon)
        binding.imgTwo.setOnClickListener {
            if (!running && !secondMemberRunning){
                firstMemberRunTimer(binding.firstMemberTimerTextView)
                secondMemberRunTimer(binding.secondMemberTimerTextView)
                binding.imgTwo.setBackgroundResource(R.drawable.ic_play_btn)
            }
        }

        binding.firstMemberTimerTextView.setOnClickListener {
            if (!running){
                running = true
                secondMemberRunning = false
            } else{
                running = false
                secondMemberRunning = true
            }
        }

        binding.secondMemberTimerTextView.setOnClickListener {
            if (!secondMemberRunning){
                secondMemberRunning = true
                running = false
            } else{
                secondMemberRunning = false
                running = true
            }
        }

        binding.imgThree.setOnClickListener {

            running = false
            seconds = 0

            secondMemberRunning = false
            secondMemberSeconds = 0

        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
        savedInstanceState.putInt("secondMemberSeconds", secondMemberSeconds)
        savedInstanceState.putBoolean("secondMemberRunning", secondMemberRunning)
        savedInstanceState.putBoolean("secondMemberWasRunning", secondMemberWasRunning)
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false

        secondMemberWasRunning = secondMemberRunning
        secondMemberRunning = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }

        if (secondMemberWasRunning) {
            secondMemberRunning = true
        }
    }

    private fun firstMemberRunTimer(textView1: TextView) {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, secs)
                textView1.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun secondMemberRunTimer(textView2: TextView) {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val minutes = secondMemberSeconds % 3600 / 60
                val secs = secondMemberSeconds % 60
                val time: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, secs)
                textView2.text = time
                if (secondMemberRunning) {
                    secondMemberSeconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}