package tml.lab.databaselab

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import tml.lab.databaselab.models.ITodoTaskDao
import tml.lab.databaselab.models.TodoTask


class TodoTaskRepository(private val todoTaskDao: ITodoTaskDao)  {
    val allTasks: LiveData<List<TodoTask>> = todoTaskDao.getAllTasks()

    @WorkerThread
    suspend fun insert(task:TodoTask) {
        todoTaskDao.insert(task)
    }
}