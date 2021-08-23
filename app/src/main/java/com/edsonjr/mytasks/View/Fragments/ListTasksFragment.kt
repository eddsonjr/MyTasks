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

        initListeners()

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListVMObserver(view)

    }


    //este metodo e responsavel por configurar o observer do viewmodel e solicitar a inicializacao
    //da recyclerview passando tambem os dados de lista de tasks para o adapter
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



    //este metodo serve para chamar o fragment  de adicionar / atualizar  tasks
    private fun loadAddUpdateTaskFragment(fragmentManager: FragmentManager?) {
        fragmentManager?.commit {
            replace<SaverUpdateTaskFragment>(R.id.fragmentContainer)
            setReorderingAllowed(false)
            addToBackStack(null)
        }


    }



    //este metodo server para inicializar todos os eventos de click listeners
    private fun initListeners(){

        val fragmentManager = activity?.supportFragmentManager

        //evento para o click de add new task - float button
        //Metodo para adiconar evento de click no FAB - para adicionar uma task nova
        binding.addTaskFloatbutton.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            loadAddUpdateTaskFragment(fragmentManager)
        }

        //Mark: Os eventos abaixo dizem respeito ao menu more dos itens da recyclerview

        //EDITANDO uma task - evento de click do popmenu dos itens da recyclerview
        adapter.listenerEdit = {
            Log.d(TAG,"EDITANDO a tarefa: ${it.id} -  ${it.title} ")
            fragmentManager?.setFragmentResult("task_update", bundleOf("taskToUpdate" to it))
            loadAddUpdateTaskFragment(fragmentManager)
        }


        //REMOVENDO  uma task - evento de click do popmenu dos itens da recyclerview
        adapter.listenerDelete = {
            Log.d(TAG,"REMOVENDO a tarefa:  ${it.id} -  ${it.title} ")
            viewModel.delete(it)
            val newTaskList = viewModel.taskList.value
            adapter.updateRecyclerView(newTaskList!!)
        }
    }

}