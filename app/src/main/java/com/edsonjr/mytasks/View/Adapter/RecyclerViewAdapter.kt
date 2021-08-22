package com.edsonjr.mytasks.View.Adapter

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
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
import com.edsonjr.mytasks.databinding.ItemTaskRecyclerviewBinding

class RecyclerViewAdapter(private val taskList: List<Task>,private val clickListener:(Task)->Unit):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}


    class ViewHolder(var binding: ItemTaskRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task,clickListener:(Task)->Unit){

            binding.txtTaskTitleItemRecycler.text = item.title

            if(item.important) binding.txtTaskImportantItemRecycler.visibility = View.VISIBLE else View.GONE


            //acao de click na celula da recyclerview
            itemView.setOnClickListener {
                clickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskRecyclerviewBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList.get(position),clickListener)

    }

    override fun getItemCount() = taskList.size


}