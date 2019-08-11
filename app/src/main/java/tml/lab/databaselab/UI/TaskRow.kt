package tml.lab.databaselab.UI

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import tml.lab.databaselab.Book
import tml.lab.databaselab.R
import tml.lab.databaselab.models.TodoTask

class TaskRow(context:Context, attrs: AttributeSet)
    :LinearLayout(context, attrs) {
    fun populate(task: TodoTask) {
        findViewById<TextView>(R.id.task_text).text = task.text
    }
}