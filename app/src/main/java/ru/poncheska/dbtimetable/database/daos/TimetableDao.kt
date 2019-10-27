package ru.poncheska.dbtimetable.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import ru.poncheska.dbtimetable.database.entities.Timetable

@Dao
interface TimetableDao {
    @Insert
    fun insert(timetable: Timetable)

    @Delete
    fun delete(timetable: Timetable)
}