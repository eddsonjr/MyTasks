package com.edsonjr.mytasks.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {

    private val TAG = "[MainActivity]"
    private var viewModel: TaskViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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


    //somente para testes
    private fun testSaveTask() {
        this.viewModel?.insertTask(Task("TASK ALTERACAO_PRIMARY_KEY","alteracao para primary key separada","22/22/22","12:45",true,false))

    }


    //somente para testes
    private fun testLoadFragmentSaveUpdateTask() {
        val saveUpdateFragment = SaverUpdateTaskFragment()
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragmentContainer,saveUpdateFragment)
        fragment.commit()
    }

}