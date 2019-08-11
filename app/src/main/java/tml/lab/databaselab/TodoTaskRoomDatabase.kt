package tml.lab.databaselab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tml.lab.databaselab.models.ITodoTaskDao
import tml.lab.databaselab.models.TodoTask

@Database(entities = [TodoTask::class], version = 1)
public abstract class TodoTaskRoomDatabase
    : RoomDatabase() {
    abstract fun taskDao(): ITodoTaskDao

    companion object {
        @Volatile
        private var INSTANCE: TodoTaskRoomDatabase? = null

        fun getDatabase(context: Context): TodoTaskRoomDatabase {
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
                    //.fallbackToDestructiveMigration() //reset DB
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}