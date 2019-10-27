package ru.poncheska.dbtimetable.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.poncheska.dbtimetable.database.entities.Lesson

@Dao
interface LessonDao {
    @Insert
    fun insert(lesson:Lesson)

    @Delete
    fun delete(lesson:Lesson)

    data class TeacherWithLessons(
        val name:String,
        val list:String
    )

    @Query("SELECT teacher_table.name, GROUP_CONCAT(lesson_table.name) AS list  from lesson_table INNER JOIN link_table ON" +
            " lesson_table.lessonId == link_table.lessonId INNER JOIN teacher_table ON" +
            " link_table.teacherId == teacher_table.teacherId GROUP BY teacher_table.name")
    fun getTeacherQuerry():List<TeacherWithLessons>
}