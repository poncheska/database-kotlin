package ru.poncheska.dbtimetable.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "group_table",
    foreignKeys = [
        (ForeignKey(
            entity = Timetable::class,
            parentColumns = ["timetableId"],
            childColumns = ["timetableId"]
        ))
    ])
data class Group(

    @PrimaryKey(autoGenerate = true)
    var groupId : Long = 0L,

    var numberOfStudents:Int = 0,

    var timetableId : Long = 0L
)