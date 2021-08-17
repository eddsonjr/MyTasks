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
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory
import com.edsonjr.mytasks.databinding.FragmentListTasksBinding
import com.edsonjr.mytasks.databinding.FragmentSaverUpdateTaskBinding


class SaverUpdateTaskFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels() //view model compartilhado
    private val TAG = "[SaveUpdateFrag]" //para fins de debug

    private var _binding: FragmentSaverUpdateTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //usando viewbinding para inflar e manipular o layout
        _binding = FragmentSaverUpdateTaskBinding.inflate(inflater, container, false)
        val view = binding.root


        //listener do fragment manager, responsavel por verificar se o usuario selecionou
        //uma task no fragment de listagem e carregar os dados na ui, permitindo update da task
        setFragmentResultListener("task_update"){ key, result ->
            val task = result.getSerializable("taskToUpdate")
            Log.d(TAG,"Task recebida: ${task.toString()}")
            configUIToUpdateTask(task as Task)
        }

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    //metodo para carregar a view com dados vindos do fragment manager
    private fun configUIToUpdateTask(task: Task){
        //alterando os elementos visuais para comportar as informacoes das tasks
        binding.btnCriarTarefa.setText(getString(R.string.btn_update_task)) //altera o texto do btn

        binding.txtTaskTitle.editText?.setText(task.title)



    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}