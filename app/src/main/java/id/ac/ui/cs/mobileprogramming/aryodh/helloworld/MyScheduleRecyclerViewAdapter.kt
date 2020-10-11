package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

import id.ac.ui.cs.mobileprogramming.aryodh.helloworld.schedule.ScheduleContent.ScheduleItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyScheduleRecyclerViewAdapter(
    private val values: List<ScheduleItem>,
    private val viewModel: ScheduleViewModel?
) : RecyclerView.Adapter<MyScheduleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_schedule_list, parent,    false)
        Log.d("######### LOG #########", "MyScheduleRecyclerViewAdapter: onCreateViewHolder called")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.title
        holder.itemView.setOnClickListener{
            viewModel?.setSchedule(item.details)
            (holder.itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.schedule_list_fragment, ScheduleDetailFragment()).commit()
            Log.d("######### LOG #########", "MyScheduleRecyclerViewAdapter: ScheduleDetail onClick Called")
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}