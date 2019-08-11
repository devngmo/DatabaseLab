package tml.lab.databaselab

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import tml.lab.databaselab.models.ITodoTaskDao
import tml.lab.databaselab.models.TodoTask


class TodoTaskRepository(private val todoTaskDao: ITodoTaskDao)  {
    val allTasks: LiveData<List<TodoTask>> = todoTaskDao.getAllTasks()

    @WorkerThread
    suspend fun insert(task:TodoTask) {
        Log.d("DBGLOG", "TodoTaskRepository:insert new task '${task.text}'")
        todoTaskDao.insert(task)
    }
}