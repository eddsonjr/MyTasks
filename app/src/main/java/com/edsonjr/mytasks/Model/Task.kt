package com.edsonjr.mytasks.Model

data class Task(
    val title: String,
    val description: String?,
    val date: String?,
    val hour: String?,
    val important: Boolean = false,
    val completed: Boolean = false
)