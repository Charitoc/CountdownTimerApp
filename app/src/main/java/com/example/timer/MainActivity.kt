package com.example.timer

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Layout
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

//    abstract val myTime: Int
    lateinit var editText: EditText
    var currentSeconds : Long = 0
    var selectedSeconds = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            playButton.setOnClickListener(View.OnClickListener {
                try{
                selectedSeconds = editTextTime.text.toString().toInt()

                    if (currentSeconds.toInt() != 0){
                        startTimer(currentSeconds)
                    }else{
                        startTimer(selectedSeconds.toLong())}
                } catch (e: Exception){
                    Toast.makeText(this,"Please set seconds", Toast.LENGTH_LONG).show()
                }


            })
    }


    private fun startTimer(selectedSeconds: Long) {
        editTextTime.isEnabled = false
        var remainingMin: Int
        val countDownTimer = object : CountDownTimer(( selectedSeconds * 1000), 100) {

            override fun onTick(leftTimeInMilliseconds: Long) {
                val seconds = leftTimeInMilliseconds / 1000
                currentSeconds = seconds
                progressBar.max = selectedSeconds.toInt()
                progressBar.progress = seconds.toInt()
                textTimer.text = String.format("%02d", leftTimeInMilliseconds /1000 / 60) + ":" + String.format("%02d", leftTimeInMilliseconds /1000  %60)

            }

            override fun onFinish() {
                if (textTimer.getText().equals("00:00")) {
                    textTimer.setText("END")
                    editTextTime.isEnabled = true
                } else {
                    textTimer.setText("0")
                    progressBar.setProgress(0)
                }
            }
        }.start()
        pauseButton.setOnClickListener(View.OnClickListener {
            countDownTimer.cancel()
            editTextTime.isEnabled = true
        })
        restartButton.setOnClickListener(View.OnClickListener {
            countDownTimer.cancel()
            countDownTimer.start()
            editTextTime.isEnabled = false
        })



    }

}