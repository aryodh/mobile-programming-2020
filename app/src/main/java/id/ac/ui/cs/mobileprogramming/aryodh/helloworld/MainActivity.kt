package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private external fun charCount(input_name: String) : String

    companion object {
        // Used to load the 'jni' library on application startup.
        init {
            System.loadLibrary("charCounter")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val result = charCount(inputText.text.toString())
                charTotal.text = result
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}