package com.edsonjr.mytasks.View.Adapter

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.View.Fragments.SaverUpdateTaskFragment

class RecyclerViewAdapter(private val taskList: List<Task>,private val clickListener:(Task)->Unit):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {



    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var important_txt = view.findViewById<TextView>(R.id.txt_task_important_item_recycler)
        var task_title = view.findViewById<TextView>(R.id.txt_taskTitle_item_recycler)
        var task_date = view.findViewById<TextView>(R.id.txt_dateTask_item_recycler)
        var task_hour = view.findViewById<TextView>(R.id.txt_taskHour_item_recycler)

        fun bind(item: Task,clickListener:(Task)->Unit){

            this.task_title.text = item.title

            if(item.important) important_txt.visibility = View.VISIBLE else View.GONE
            if(item.date.isNullOrEmpty()) task_date.text = ""  else task_date.text = item.date
            if(item.hour.isNullOrEmpty()) task_hour.text = "" else task_hour.text = item.hour



            //acao de click na celula da recyclerview
            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_recyclerview,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList.get(position),clickListener)

    }

    override fun getItemCount() = taskList.size


}