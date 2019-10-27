package ru.poncheska.dbtimetable.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "timetable_table",
    foreignKeys = [
        (ForeignKey(
            entity= Lesson::class,
            parentColumns = ["lessonId"],
            childColumns = ["lesson1"])),
        (ForeignKey(
            entity= Lesson::class,
            parentColumns = ["lessonId"],
            childColumns = ["lesson2"])),
        (ForeignKey(
            entity= Lesson::class,
            parentColumns = ["lessonId"],
            childColumns = ["lesson3"])),
        (ForeignKey(
            entity= Lesson::class,
            parentColumns = ["lessonId"],
            childColumns = ["lesson4"])),
        (ForeignKey(
            entity= Lesson::class,
            parentColumns = ["lessonId"],
            childColumns = ["lesson5"]))
    ]
)
data class Timetable(
    @PrimaryKey(autoGenerate = true)
    var timetableId:Long = 0L,

    var lesson1 : Long = 0L,

    var lesson2 : Long = 0L,

    var lesson3 : Long = 0L,

    var lesson4 : Long = 0L,

    var lesson5 : Long = 0L
)