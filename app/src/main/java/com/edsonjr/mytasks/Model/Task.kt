package com.edsonjr.mytasks.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "task_table")
data class Task(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "date")
    val date: String?,


    @ColumnInfo(name = "hour")
    val hour: String?,

    @ColumnInfo(name = "important")
    val important: Boolean = false,

    @ColumnInfo(name = "completed")
    val completed: Boolean = false
): Serializable