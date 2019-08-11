package tml.lab.databaselab

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_todo_task_editor.*

class TodoTaskEditorActivity : AppCompatActivity() {
    companion object {
        val TASK_PROPERTY_TEXT = "text"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_task_editor)

        title = "Add new task"
    }

    fun onClickButtonSave(v: View) {
        val saveIntent = Intent()
        if (TextUtils.isEmpty(task_text.text.toString())) {
            setResult(Activity.RESULT_CANCELED, saveIntent)
        } else {
            saveIntent.putExtra(TASK_PROPERTY_TEXT, task_text.text.toString())
            setResult(Activity.RESULT_OK, saveIntent)
        }
        finish()
    }
}
