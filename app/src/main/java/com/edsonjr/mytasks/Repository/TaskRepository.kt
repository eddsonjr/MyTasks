package com.edsonjr.mytasks.Repository

import com.edsonjr.mytasks.DataBase.TaskDAO
import com.edsonjr.mytasks.Model.Task

class TaskRepository(private val dao: TaskDAO) {

    val tasks = dao.getAllTasks()

    suspend fun insert(task: Task){
        dao.insertTask(task)
    }

    suspend fun update(task: Task){
        dao.updateTask(task)
    }

    suspend fun delete(task: Task){
        dao.deleteTask(task)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}