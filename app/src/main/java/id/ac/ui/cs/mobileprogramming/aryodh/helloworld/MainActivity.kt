package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("######### LOG #########", "MainActivity Passed")
        supportFragmentManager.beginTransaction().replace(R.id.schedule_list_fragment, ScheduleListFragment()).commit()
        Log.d("######### LOG #########", "MainActivity Replace Passed")
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentById(R.id.schedule_list_fragment) is ScheduleDetailFragment){
            supportFragmentManager.beginTransaction().replace(R.id.schedule_list_fragment, ScheduleListFragment()).commit()
        } else{
            super.onBackPressed()
        }
    }

}