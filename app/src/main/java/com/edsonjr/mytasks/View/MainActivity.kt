package com.edsonjr.mytasks.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.R
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.View.Fragments.ListTasksFragment
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "[MainActivity]"
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


}