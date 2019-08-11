package tml.lab.databaselab.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todotask_table")
class TodoTask(@PrimaryKey @ColumnInfo(name = "text") val text: String)
