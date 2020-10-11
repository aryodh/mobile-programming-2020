package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class ScheduleDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleDetailFragment()
    }

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            viewModel = ViewModelProviders.of(activity!!).get(ScheduleViewModel::class.java)
        val rootView: View = inflater.inflate(R.layout.fragment_schedule_detail, container, false)
        val scheduleTitle: TextView = rootView.findViewById(R.id.schedule_details_title)
        val scheduleDate: TextView = rootView.findViewById(R.id.schedule_details_date)
        val scheduleTime: TextView = rootView.findViewById(R.id.schedule_details_time)
        val scheduleLocation: TextView = rootView.findViewById(R.id.schedule_details_location)
        val scheduleContent: TextView = rootView.findViewById(R.id.schedule_details_content)

        // Set the adapter
        viewModel.schedule.observe(this,
            Observer<List<String>> { scheduleList ->

                scheduleTitle.text = Html.fromHtml("<b>" + scheduleList[0] + "</b>")
                scheduleDate.text = Html.fromHtml("<b> Date: </b>" + scheduleList[1])
                scheduleTime.text = Html.fromHtml("<b> Time: </b>" + scheduleList[2])
                scheduleLocation.text = Html.fromHtml("<b> Location: </b>" + scheduleList[3])
                scheduleContent.text = scheduleList[4]
            })

        Log.d("######### LOG #########", "ScheduleDetailFragment: ScheduleDetail Created")

        return rootView
    }

}