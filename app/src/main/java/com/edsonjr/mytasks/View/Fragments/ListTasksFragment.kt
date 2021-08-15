package com.edsonjr.mytasks.View.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.View.MainActivity
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory


class ListTasksFragment : Fragment() {

    private var viewModel: TaskViewModel? = null
    private val TAG = "[ListTaskFragment]"
    private var taskList: List<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tasks, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        taskListVMObserver()
    }


    //metodo responsavel por inicializar o viewModel
    private fun initViewModel(){
        val dao = TasksDatabase.getDBInstance(requireActivity()).TaskDAO //adquirindo o dao
        val repository = TaskRepository(dao) //instanciando o repositorio que sera usado no factory
        val factory = TaskViewModelFactory(repository)  //iniciando o viewmodel factory
        this.viewModel = ViewModelProvider(requireActivity(),factory).get(TaskViewModel::class.java)

        Log.d(TAG,"Inicializado o TaskViewModel: ${this.viewModel}")
    }



    //este metodo e responsavel por configurar o observer do viewmodel
    private fun taskListVMObserver(){
        viewModel?.taskList?.observe(viewLifecycleOwner, Observer { tasks ->
            Log.d(TAG,"Numero de tasks do banco: ${tasks.size}")
            this.taskList = tasks
        })


    }


}