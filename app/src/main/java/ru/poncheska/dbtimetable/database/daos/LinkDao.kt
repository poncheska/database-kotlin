package ru.poncheska.dbtimetable.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import ru.poncheska.dbtimetable.database.entities.Link

@Dao
interface LinkDao{
    @Insert
    fun insert(link: Link)

    @Delete
    fun delete(link: Link)
}