package id.ac.ui.cs.mobileprogramming.aryodh.lab02

import android.content.Context
import android.content.SharedPreferences
import kotlin.math.log

class SharedPref(context: Context) {

    var mySharedPref: SharedPreferences

    init {
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE)
    }

    fun setDarkModeState(state:Boolean?) {
        val editor = mySharedPref.edit()
        editor.putBoolean("Dark Mode", state!!)
        editor.commit()
    }

    fun loadDarkModeState(): Boolean? {
        return mySharedPref.getBoolean("Dark Mode", false)
    }

    fun setDarkModeTitle(string: String) {
        val editor = mySharedPref.edit()
        editor.putString("Title", string)
        editor.commit()

    }

    fun loadDarkModeTitle(): String? {
        return mySharedPref.getString("Title", "Too Bright?")
    }

}
