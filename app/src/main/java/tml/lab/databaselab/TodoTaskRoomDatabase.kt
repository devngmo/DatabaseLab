package tml.lab.databaselab

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tml.lab.databaselab.models.ITodoTaskDao
import tml.lab.databaselab.models.TodoTask

@Database(entities = [TodoTask::class], version = 1)
public abstract class TodoTaskRoomDatabase
    : RoomDatabase() {
    abstract fun taskDao(): ITodoTaskDao

    companion object {
        @Volatile
        private var INSTANCE: TodoTaskRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TodoTaskRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoTaskRoomDatabase::class.java,
                    "todo_database"
                )
                    .fallbackToDestructiveMigration() //reset DB
                    .addCallback(TodoTaskDBCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


    class TodoTaskDBCallback (private val scope: CoroutineScope)
        : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { taskDB ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(taskDB.taskDao())
                }
            }
        }
        suspend fun populateDatabase(taskDao: ITodoTaskDao) {
            Log.d("DBGLOG", "populateDatabase: add sample tasks...")
            taskDao.deleteAll()

            var task = TodoTask("Implement function: Add New Task on List Activity")
            taskDao.insert(task)
            task = TodoTask("Implement Task Editor Activity")
            taskDao.insert(task)
        }
    }
}