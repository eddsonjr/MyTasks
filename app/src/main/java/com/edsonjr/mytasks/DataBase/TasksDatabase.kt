package com.edsonjr.mytasks.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edsonjr.mytasks.Model.Task


//classe responsavel por criar a gerir a instancia do Room
@Database(entities = [Task::class],version = 1)
abstract  class TasksDatabase: RoomDatabase() {

    //TODO - Criar DAO
    //abstract val TaskDAO: TaskDAO

    companion object {

        @Volatile
        private var INSTANCE: TasksDatabase? = null

        fun getDBInstance(context: Context): TasksDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,TasksDatabase::class.java,
                        "task_database"
                    ).build()
                }
                return instance
            }
        }
    }

}