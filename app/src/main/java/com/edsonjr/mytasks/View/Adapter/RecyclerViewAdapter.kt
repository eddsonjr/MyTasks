package com.edsonjr.mytasks.View.Adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R

class RecyclerViewAdapter(private val taskList: List<Task>):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var important_txt = view.findViewById<TextView>(R.id.txt_task_important_item_recycler)
        var task_title = view.findViewById<TextView>(R.id.txt_taskTitle_item_recycler)
        var task_date = view.findViewById<TextView>(R.id.txt_dateTask_item_recycler)
        var task_hour = view.findViewById<TextView>(R.id.txt_taskHour_item_recycler)

        fun bind(item: Task){

            this.task_title.text = item.title

            if(item.important) this.important_txt.visibility = View.VISIBLE else View.GONE
            if(item.date.isNullOrEmpty()) task_date.visibility = View.GONE else task_date.text = item.date
            if(item.hour.isNullOrEmpty()) task_hour.visibility = View.GONE else task_hour.text = item.hour
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_recyclerview,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList.get(position))
    }

    override fun getItemCount() = taskList.size
}