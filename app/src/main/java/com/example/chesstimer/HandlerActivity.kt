package com.example.chesstimer

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chesstimer.databinding.ActivityHandlerBinding


class HandlerActivity : AppCompatActivity() {

    lateinit var binding: ActivityHandlerBinding

    var mHandler: Handler? = null
    var mProgressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_handler)
        binding.btn.setOnClickListener {
            notificationDialog()
        }
    }

    private fun notificationDialog(){
        mHandler = Handler()
        mProgressBar= ProgressDialog(this)
        mProgressBar!!.max = 100
        mProgressBar!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        mProgressBar!!.show()
        Thread {
            for (i in 0..100) {
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                //Update the value background thread to UI thread
                mHandler!!.post { mProgressBar!!.progress = i }
            }
        }.start()
    }
}