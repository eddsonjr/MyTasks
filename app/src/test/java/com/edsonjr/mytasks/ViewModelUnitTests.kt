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
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.Repository.TaskRepository
import com.edsonjr.mytasks.ViewModel.TaskViewModel
import com.edsonjr.mytasks.ViewModel.TaskViewModelFactory
import junit.framework.Assert.assertEquals
import org.robolectric.RuntimeEnvironment.application


/*
*   Classe para realizar testes no view Model
*
*/

const val NUMBER_OF_DATA_TO_GET = 0 //numero de dados a serem adquiridos


@RunWith(RobolectricTestRunner::class)
class ViewModelTests {


    //necessario apra a configuracao do viewModel, factory, dao
    private val DAO = TasksDatabase.getDBInstance(application).TaskDAO
    private val repository = TaskRepository(DAO)
    private val viewModel = TaskViewModel(repository)

    //Objetos do tipo task para testar o vm
    private val task1 = Task(1,"Test1","desc1","00/00/0000","00:00",true,false)
    private val task2 = Task(1,"Test2","desc2","11/11/1111","11:11",true,false)




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
        val taskListFromBD = viewModel.listAllTasks().value?.size
        assertEquals(NUMBER_OF_DATA_TO_GET,0)
    }

    @Test
    fun deleteAllTasks_unitTest() {
        //TODO - CRIAR UM TESTE PARA REMOVER TODAS AS TASKS DO BANCO DE DADOS USANDO VM
    }

}
