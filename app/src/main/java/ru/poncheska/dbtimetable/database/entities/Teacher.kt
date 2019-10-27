package ru.poncheska.dbtimetable.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table")
data class Teacher(
    @PrimaryKey(autoGenerate = true)
    var teacherId : Long = 0L,

    var name : String,

    var rang : String,

    var isWorking : Boolean
)