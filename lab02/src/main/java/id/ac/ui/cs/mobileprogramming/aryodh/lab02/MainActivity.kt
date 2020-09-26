package id.ac.ui.cs.mobileprogramming.aryodh.lab02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var switch: Switch? = null
    internal lateinit var sharedpref: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        sharedpref = SharedPref(this)

        if (sharedpref.loadDarkModeState() == true) {
            setTheme(R.style.DarkTheme)
        } else {
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switch = findViewById<View>(R.id.darkModeSwitch) as Switch?
        if (sharedpref.loadDarkModeState() == true) {
            switch!!.isChecked = true
        }

        switch!!.setOnCheckedChangeListener {buttonView, isChecked ->
        if (isChecked) {
            sharedpref.setDarkModeTitle(nightMessage())
            sharedpref.setDarkModeState(true)
            restartApp()
        } else {
            sharedpref.setDarkModeTitle(lightMessage())
            sharedpref.setDarkModeState(false)
            restartApp()
        }}

        val tv1: TextView = findViewById(R.id.darkModeTitle)
        tv1.text = sharedpref.loadDarkModeTitle().toString()
    }

    fun restartApp() {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    fun nightMessage(): String {
        return "Too Dark?"
    }

    fun lightMessage(): String {
        return "Too Bright?"
    }
}