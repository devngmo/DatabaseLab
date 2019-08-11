package tml.lab.databaselab

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openSQLDelightBookManager(v:View) {
        val i = Intent(this, BookManagerActivity::class.java)
        startActivity(i)
    }

    fun open_Room_TodoList(v:View) {
        val i = Intent(this, TodolistWithRoomActivity::class.java)
        startActivity(i)
    }
}
