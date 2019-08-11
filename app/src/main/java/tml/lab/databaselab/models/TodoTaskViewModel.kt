package tml.lab.databaselab.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tml.lab.databaselab.TodoTaskRepository
import tml.lab.databaselab.TodoTaskRoomDatabase

class TodoTaskViewModel (application:Application)
    :AndroidViewModel(application) {
    private val repository: TodoTaskRepository
    val allTasks : LiveData<List<TodoTask>>

    init {
        val taskDao = TodoTaskRoomDatabase.getDatabase(application).taskDao()
        repository = TodoTaskRepository(taskDao)
        allTasks = repository.allTasks
    }

    fun insert(task:TodoTask) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }


}