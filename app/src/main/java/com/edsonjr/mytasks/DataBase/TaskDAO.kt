package com.edsonjr.mytasks.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.edsonjr.mytasks.Model.Task


//interface para trabalhar com o DAO
@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task>>


}