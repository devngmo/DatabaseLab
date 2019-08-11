package tml.lab.databaselab.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ITodoTaskDao {
    @Query("SELECT * FROM todotask_table ORDER BY text ASC")
    fun getAllTasks():LiveData<List<TodoTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TodoTask)

    @Query("DELETE FROM todotask_table")
    fun deleteAll()
}