package com.edsonjr.mytasks.View.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory


class SaverUpdateTaskFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels() //view model compartilhado
    private val TAG = "[SaveUpdateFrag]" //para fins de debug



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_saver_update_task, container, false)

        //listener do fragment manager, responsavel por verificar se o usuario selecionou
        //uma task no fragment de listagem e carregar os dados na ui, permitindo update da task
        setFragmentResultListener("task_update"){ key, result ->
            val task = result.getSerializable("taskToUpdate")
            Log.d(TAG,"Task recebida: ${task.toString()}")
        }


        // Inflate the layout for this fragment
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    //metodo para carregar a view com dados vindos do fragment manager







}