package ru.poncheska.dbtimetable.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson_table"
)
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    var lessonId : Long = 0L,

    var name : String,

    var room : String
)