package id.ac.ui.cs.mobileprogramming.aryodh.helloworld

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class WifiListAdapter(
    private val values: List<String>
) : RecyclerView.Adapter<WifiListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("WifiListAdapter", "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wifi_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeDetailValue = values[position]
        holder.wifiNumber.text = (position+1).toString()
        holder.wifiContent.text = recipeDetailValue
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wifiNumber: TextView = view.findViewById(R.id.wifi_number)
        val wifiContent: TextView = view.findViewById(R.id.wifi_content)
    }
}

