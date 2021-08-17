package com.edsonjr.mytasks.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edsonjr.mytasks.Model.Task
import com.edsonjr.mytasks.Repository.TaskRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository):ViewModel() {

    var taskList = repository.tasks

    //metodo para inserir novas tasks no banco
    fun insertTask(task: Task): Job = viewModelScope.launch {
        repository.insert(task)
    }


    //metodo para atualizar uma task no banco
    fun updateTask(task: Task): Job = viewModelScope.launch {
        repository.update(task)
    }


    //metodo para remover uma task do banco
    fun delete(task: Task): Job = viewModelScope.launch {
        repository.delete(task)
    }


    //metodo para remover todos os dados do banco de dados
    fun clearBD(): Job = viewModelScope.launch {
        repository.deleteAll()
    }

}