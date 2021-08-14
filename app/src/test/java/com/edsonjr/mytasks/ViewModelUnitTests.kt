package com.edsonjr.mytasks


import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider
import com.edsonjr.mytasks.DataBase.TasksDatabase
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory
import org.robolectric.RuntimeEnvironment.application


/*
*   Classe para realizar testes no view Model
*
*/


@RunWith(RobolectricTestRunner::class)
class ViewModelTests {


    //necessario apra a configuracao do viewModel, factory, dao
    private val DAO = TasksDatabase.getDBInstance(application).TaskDAO
    private val repository = TaskRepository(DAO)
    private val viewModel = TaskViewModel(repository)


    @Test
    fun insertTask_unitTest() {
        //TODO - CRIAR TESTE PARA INSERIR UMA TASK NO BANCO DE DADOS USANDO VM
    }


    @Test
    fun deleteTask_unitTest() {
        //TODO - CRIAR TESTE PARA REMOVER UMA TASK NO BANCO DE DADOS USANDO VM
    }


    @Test
    fun updateTask_unitTest() {
        //TODO - CRIAR UM TESTE PARA FAZER UPDATE DE UMA TASK NO BANCO USANDO VM
    }


    @Test
    fun listTasks_unitTest(){
        //TODO - CRIAR UM TESTE PARA LISTAR TODAS AS TASKS DO BANCO DE DADOS USANDO VM
    }

    @Test
    fun deleteAllTasks_unitTest() {
        //TODO - CRIAR UM TESTE PARA REMOVER TODAS AS TASKS DO BANCO DE DADOS USANDO VM
    }

}
