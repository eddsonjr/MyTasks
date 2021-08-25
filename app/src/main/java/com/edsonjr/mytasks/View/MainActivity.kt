package com.edsonjr.mytasks.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.View.Fragments.ListTasksFragment
import com.edsonjr.mytasks.View.Fragments.SaverUpdateTaskFragment
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory
import com.edsonjr.mytasks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "[MainActivity]"
    private var viewModel: TaskViewModel? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        startTaskListFragment()

    }


    //este metodo e responsavel por carregar o primeiro fragment, que mostra uma lista de
    //tasks vindas do banco de dados
    private fun startTaskListFragment() {
        val listTaskFragment = ListTasksFragment()
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragmentContainer,listTaskFragment)
        fragment.commit()
    }



    //metodo responsavel por inicializar o viewModel
    private fun initViewModel(){
        val dao = TasksDatabase.getDBInstance(this).TaskDAO //adquirindo o dao
        val repository = TaskRepository(dao) //instanciando o repositorio que sera usado no factory
        val factory = TaskViewModelFactory(repository)  //iniciando o viewmodel factory
        viewModel = ViewModelProvider(this,factory).get(TaskViewModel::class.java)
        Log.d(TAG,"Inicializado o TaskViewModel: ${this.viewModel}")
    }




    //esta funcao e responsavel por verificar se ha dados no banco e caso hajam, chamar o framgent
    //caso negativo, carregar o layout indicando que nao ha tasks cadastradas ainda
    private fun checkAndInitViews() {
        if(viewModel?.taskList?.value?.isNotEmpty()!!){
            Log.d(TAG,"Taks cadastradas no banco de dados. Carregando fragment com recyclerview")
            binding.noTasksFrame.visibility = View.GONE
            binding.fragmentContainer.visibility = View.VISIBLE
            startTaskListFragment()
        }else{
            Log.d(TAG,"Sem tasks. Mostrando framelayout de tasks nao cadastradas...")
            binding.noTasksFrame.visibility = View.VISIBLE
            binding.fragmentContainer.visibility = View.GONE
        }

    }





}