package com.edsonjr.mytasks.View.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.View.MainActivity
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory


class ListTasksFragment : Fragment() {

    private val TAG = "[ListTaskFragment]" //para fins de debug
    private var taskList: List<Task>? = null //lista de tasks vindas do banco
    private val viewModel: TaskViewModel by activityViewModels() //view model compartilhado
    private var recyclerView: RecyclerView? = null //recyclerview de tasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_tasks, container, false)



        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListVMObserver()

    }


    //este metodo e responsavel por configurar o observer do viewmodel
    private fun taskListVMObserver(){
        viewModel?.taskList?.observe(viewLifecycleOwner, Observer { tasks ->
            Log.d(TAG,"Numero de tasks do banco: ${tasks.size}")
            this.taskList = tasks
        })
    }


    //este metodo e resposnavel por inicializar a recyclerview
    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.taskListRecyclerview) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(activity)

    }

}