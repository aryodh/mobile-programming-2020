package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    var isRunning: Boolean = false
    var backIsPressed: Boolean = false
    var isPause: Boolean = false
    var isFinish: Boolean = false
    var time_in_seconds = 0L
    var lastTime = 0L
    val timerExecutor = Executors.newFixedThreadPool(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            hideKeyboard()
            if (isRunning && !isPause) {
                pauseTimer()
            } else if (isPause) {
                isPause = false
                time_edit_text.clearFocus();
                startTimer(lastTime)
            } else {
                val time  = time_edit_text.text.toString()
                time_in_seconds = time.toLong()
                startTimer(time_in_seconds)
            }
        }
        reset.setOnClickListener {
            resetTimer()
        }
    }

    private fun pauseTimer() {
        isPause = true
        isRunning = false
        button.text = "Continue"
        reset.visibility = View.VISIBLE
    }

    private fun startTimer(time_in_seconds: Long) {
        val worker = Runnable {
            timer.setBackgroundResource(R.drawable.round_bg)
            var time = time_in_seconds
            while (time > 0 && !isPause) {
                updateTextUI(time)
                time -= 1
                sleep(1000)
            }
            if (isPause) {
                lastTime = time
            } else {
                isFinish = true
                Stopwatchfinsih()
            }
        }
        timerExecutor.execute(worker)
        isRunning = true
        button.text = "Pause"
    }

    private fun Stopwatchfinsih() {
        isPause = false
        isRunning = false
        timer.text = "Finish!"
        timer.setBackgroundResource(R.drawable.round_bg_finish)
        this@MainActivity.runOnUiThread(java.lang.Runnable {
            this.button.text = "Start"
        })
    }

    private fun resetTimer() {
        val time  = time_edit_text.text.toString()
        time_in_seconds = time.toLong()
        isPause = false
        val startButton: Button = findViewById(R.id.button)
        startButton.text = "Start"
        updateTextUI(time_in_seconds)
        timer.setBackgroundResource(R.drawable.round_bg)
        reset.visibility = View.INVISIBLE
    }

    private fun updateTextUI(time_in_seconds: Long) {
        val minute = (time_in_seconds) / 60
        val seconds = (time_in_seconds) % 60

        if (minute > 9 && seconds > 9) {
            timer.text = "$minute:$seconds"
        } else if (minute < 10 && seconds > 9) {
            timer.text = "0$minute:$seconds"
        } else if (minute > 9 && seconds < 10) {
            timer.text = "$minute:0$seconds"
        } else {
            timer.text = "0$minute:0$seconds"
        }

    }

    override fun onBackPressed() {
        if(backIsPressed) {
            super.onBackPressed();
        } else {
            backIsPressed = true
            Toast.makeText(this@MainActivity, "Tekan Sekali Lagi untuk Keluar", Toast.LENGTH_SHORT).show()
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }



}