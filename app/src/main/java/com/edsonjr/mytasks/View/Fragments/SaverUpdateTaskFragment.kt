package com.edsonjr.mytasks.View.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.Extensions.format
import com.edsonjr.mytasks.Extensions.text
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory
import com.edsonjr.mytasks.databinding.FragmentListTasksBinding
import com.edsonjr.mytasks.databinding.FragmentSaverUpdateTaskBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*


class SaverUpdateTaskFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels() //view model compartilhado
    private val TAG = "[SaveUpdateFrag]" //para fins de debug
    private var _binding: FragmentSaverUpdateTaskBinding? = null
    private val binding get() = _binding!!
    private var isUpdatingTask: Boolean = false //serve para informar se ira salvar ou atualizar


    var taskID: Long = 0



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


        //configurando os eventos de click e listeners
        initDateHourListeners()
        configureButtonsListeners()



        //listener do fragment manager, responsavel por verificar se o usuario selecionou
        //uma task no fragment de listagem e carregar os dados na ui, permitindo update da task
        setFragmentResultListener("task_update"){ key, result ->
            val task = result.getSerializable("taskToUpdate")
            Log.d(TAG,"Task recebida: ${task.toString()}")
            configUIToUpdateTask(task as Task)
            this.isUpdatingTask = true
            this.taskID = task.id

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
    private fun configureButtonsListeners() {

        //caso o usuario click em cancelar, retornar para tela anterior
        binding.btnCancelar.setOnClickListener {
            backToListTaskFragment()
        }

        //caso o usuario click no botao de salvar (ou update), disparar acao de update ou save
        binding.btnCriarTarefa.setOnClickListener {
            val task = captureUserInputAndReturnTask()

            if(!isUpdatingTask){
                Log.d(TAG,"Adicionando uma nova task no banco...")
                if(binding.txtTaskTitle.text.isEmpty() || binding.txtTaskTitle.text.isBlank()){
                    Log.d(TAG,"ATENCAO: Usuario precisa escrever um titulo para a tarefa")
                    Toast.makeText(activity?.baseContext,"Atenção: Necessário informar título da tarefa",
                    Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.insertTask(task)
                    backToListTaskFragment() //voltando para tela de listagem de tasks
                }
            }else{
                Log.d(TAG,"Atualizando a task ${task.title}...")
                viewModel.updateTask(task)
                backToListTaskFragment() //voltando para tela de listagem de tasks
            }
        }
    }



    //metodo para carregar o fragment
    private fun backToListTaskFragment() {
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.popBackStack()
    }


    //captura os dados dos componentes de UI e retorna um objeto do tipo Task, que pode ser
    //atualizado ou adicionado
    private fun captureUserInputAndReturnTask(): Task {
        var task: Task? = null
        val title = binding.txtTaskTitle?.text.toString()
        val descripiton = binding.txtTaskDescription?.text.toString()
        val important = binding.importanTaskSwitch.isChecked

        task = Task(title,descripiton,binding.txtDateTask.text,binding.txtHourTask.text,important,false,taskID)
        Log.d(TAG,"Task com dados de UI: $task")
        return task

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun initDateHourListeners() {
        val fragmentManager = activity?.supportFragmentManager
        //evento de click pra hora
        binding.txtHourTask.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener{
                val minute =  if(timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                val hora =  if(timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val hour = "$hora:$minute"
                binding.txtHourTask.text = hour!!
            }

            timePicker.show(fragmentManager!!,"HOUR_PICKER_TAG")
        }


        //evento de click para data
        binding.txtDateTask.editText?.setOnClickListener {
            val datePicker =  MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                //usuario clicando no botao de ok do date picker
                //retornando o timestamp

                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1

                //trabalhando com extensions
                val date = Date(it + offset).format()
                binding.txtDateTask.text = date!!
            }
            datePicker.show(fragmentManager!!,"DATE_PICKER_TAG")
        }
    }

}