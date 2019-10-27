package ru.poncheska.dbtimetable.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "link_table",
    primaryKeys = ["lessonId", "teacherId"]
)
data class Link (
    var lessonId : Long,

    var teacherId : Long
)