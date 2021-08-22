package com.edsonjr.mytasks.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.View.Adapter.RecyclerViewAdapter
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.databinding.FragmentListTasksBinding
import com.edsonjr.mytasks.databinding.FragmentSaverUpdateTaskBinding


class ListTasksFragment : Fragment() {

    private val TAG = "[ListTaskFragment]" //para fins de debug
    private val viewModel: TaskViewModel by activityViewModels() //view model compartilhado
    private var recyclerView: RecyclerView? = null //recyclerview de tasks
    private val adapter = RecyclerViewAdapter()

    //para trabalhar com o binding
    private var _binding: FragmentListTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        addNewTaskClickListener()

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListVMObserver(view)

    }


    //este metodo e responsavel por configurar o observer do viewmodel
    private fun taskListVMObserver(view: View){
        viewModel?.taskList?.observe(viewLifecycleOwner, Observer { tasks ->
            Log.d(TAG,"Numero de tasks do banco: ${tasks.size}")
            initRecyclerView(view,tasks)
        })
    }


    //este metodo e resposnavel por inicializar a recyclerview
    private fun initRecyclerView(view: View,tasks: List<Task>) {
        recyclerView = binding.taskListRecyclerview
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter
        adapter.updateRecyclerView(tasks)

    }


    //metodo que ira executar o evento de click da celula da recycleview
    private fun itemClickListener(task: Task){

        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.setFragmentResult("task_update", bundleOf("taskToUpdate" to task))
        loadAddUpdateTaskFragment(fragmentManager)
    }



    //este metodo serve para chamar o fragment  de adicionar / atualizar  tasks
    private fun loadAddUpdateTaskFragment(fragmentManager: FragmentManager?) {
        fragmentManager?.commit {
            replace<SaverUpdateTaskFragment>(R.id.fragmentContainer)
            setReorderingAllowed(false)
            addToBackStack(null)
        }


    }


    //Metodo para adiconar evento de click no FAB - para adicionar uma task nova
    private fun addNewTaskClickListener() {
        binding.addTaskFloatbutton.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            loadAddUpdateTaskFragment(fragmentManager)
        }
    }



    //este metodo server para inicializar todos os eventos de click listeners
    private fun initListeners(){

        //evento para o click de add new task - float button
        binding.addTaskFloatbutton.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            loadAddUpdateTaskFragment(fragmentManager)
        }



    }

}