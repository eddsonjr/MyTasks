package com.edsonjr.mytasks.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
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

    private var isUpdatingTask: Boolean = false


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


        //configurando os eventos de click
        configureSaveUpdateButtons()


        //listener do fragment manager, responsavel por verificar se o usuario selecionou
        //uma task no fragment de listagem e carregar os dados na ui, permitindo update da task
        setFragmentResultListener("task_update"){ key, result ->
            val task = result.getSerializable("taskToUpdate")
            Log.d(TAG,"Task recebida: ${task.toString()}")
            configUIToUpdateTask(task as Task)
            this.isUpdatingTask = true

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

        binding.txtTaskTitle.editText?.setText(task.title) //configurando o titulo da task

        //configurando a descricao da task
        if(task.description?.isNotEmpty() == true)
            binding.txtTaskDescription.editText?.setText(task.description)

        //configurando a data da task
        if(task.date?.isNotEmpty() == true)
            binding.txtDateTask.editText?.setText(task.date)

        //configurando a hora da task
        if(task.hour?.isNotEmpty() == true)
            binding.txtHourTask.editText?.setText(task.hour)

        //configurando se e importante ou nao
        if(task.important)
            binding.importanTaskSwitch.isChecked = true

    }



    //metodo que serve para trabalhar com os eventos de click dos botoes de salvar/atualizar
    //e cancelar
    private fun configureSaveUpdateButtons() {

        //caso o usuario click
        binding.btnCancelar.setOnClickListener {
            loadFragment()

        }

    }



    //metodo para carregar o fragment
    private fun loadFragment() {
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.commit {
            replace<ListTasksFragment>(R.id.fragmentContainer)
            setReorderingAllowed(false)
            addToBackStack(null)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }







}