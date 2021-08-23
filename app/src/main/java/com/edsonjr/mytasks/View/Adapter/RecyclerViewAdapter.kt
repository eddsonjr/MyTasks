package com.edsonjr.mytasks.View.Adapter

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.opengl.Visibility
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.View.Fragments.SaverUpdateTaskFragment
import com.edsonjr.mytasks.databinding.ItemTaskRecyclerviewBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var taskList: List<Task>? = null
    var context: Context? = null

    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}
    var listenerTaskDone: (Task) -> Unit = {}


    //atualiza os dados da recyclerview
    fun updateRecyclerView(tasks: List<Task>){
        this.taskList = null
        this.taskList = tasks
        notifyDataSetChanged()
    }


    inner class ViewHolder(var binding: ItemTaskRecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Task){

            binding.txtTaskTitleItemRecycler.text = item.title //titulo da tarefa

            //se a tarefa estiver completa, tachar o texto de tittulo da tarefa
            if(item.completed){
                binding.txtTaskTitleItemRecycler.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            if(item.important) binding.txtTaskImportantItemRecycler.visibility = View.VISIBLE else View.GONE


            //Mark - Click listener do menu
            //menu de pop da celula com as opcoes de editar, remover e finalizar tarefa
            binding.itemRecyclerViewMore.setOnClickListener {
                showMenuPopUp(item)
            }

        }



        //infla o pop menu de more actions da recyclerview
        private fun showMenuPopUp(task: Task) {
            val itemMoreActions = binding.itemRecyclerViewMore
            val popMenu = PopupMenu(itemMoreActions.context,itemMoreActions)
            popMenu.menuInflater.inflate(R.menu.item_recyclerview_menu,popMenu.menu)
            popMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit -> listenerEdit(task)
                    R.id.action_delete -> listenerDelete(task)
                    R.id.action_TaskDone -> listenerTaskDone(task)
                }

                return@setOnMenuItemClickListener true
            }
            popMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskRecyclerviewBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList?.get(position)!!)

    }

    override fun getItemCount() = taskList?.size!!


}