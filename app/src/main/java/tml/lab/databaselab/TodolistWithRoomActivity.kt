package tml.lab.databaselab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_todolist_with_room.*
import kotlinx.android.synthetic.main.content_todolist_with_room.*
import tml.lab.databaselab.UI.TaskRow
import tml.lab.databaselab.models.TodoTask
import tml.lab.databaselab.models.TodoTaskViewModel

class TodolistWithRoomActivity : AppCompatActivity() {
    lateinit var taskViewModel: TodoTaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist_with_room)
        setSupportActionBar(toolbar)

        val taskListAdapter = TaskAdapter(application)
        lvTasks.layoutManager = LinearLayoutManager(this)
        lvTasks.adapter = taskListAdapter

        taskViewModel = ViewModelProviders.of(this).get(TodoTaskViewModel::class.java)
        taskViewModel.allTasks.observe(this, Observer{
            tasks -> tasks?.let { taskListAdapter.setTasks(it) }
        })

        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //   .setAction("Action", null).show()
            openTaskEditor_addNew()
        }
    }

    private fun openTaskEditor_addNew() {
        val i = Intent(this, TodoTaskEditorActivity::class.java)
        startActivity(i)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    class TaskAdapter internal constructor(context:Context)
        : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            holder.setTask(tasks[position])
        }

        val inflater: LayoutInflater = LayoutInflater.from(context)
        var tasks = emptyList<TodoTask>()

        class TaskViewHolder(val taskRow: TaskRow): RecyclerView.ViewHolder(taskRow) {
            fun setTask(task: TodoTask) {
                taskRow.populate(task)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val taskRow = inflater.inflate(R.layout.task_row, parent, false) as TaskRow
            return TaskViewHolder(taskRow)
        }

        internal fun setTasks(tasks: List<TodoTask>) {
            this.tasks  = tasks
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = tasks.size
    }

}
