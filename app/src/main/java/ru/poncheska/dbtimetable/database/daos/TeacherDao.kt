package ru.poncheska.dbtimetable.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.poncheska.dbtimetable.database.entities.Teacher

@Dao
interface TeacherDao {
    @Insert
    fun insert(teacher:Teacher)

    @Delete
    fun delete(teacher:Teacher)

    data class LessonWithTeachers(
        val name:String,
        val list:String
    )

    @Query("SELECT lesson_table.name, GROUP_CONCAT(teacher_table.name) AS list  from lesson_table INNER JOIN link_table ON" +
            " lesson_table.lessonId == link_table.lessonId INNER JOIN teacher_table ON" +
            " link_table.teacherId == teacher_table.teacherId GROUP BY lesson_table.name")
    fun getTeacherQuerryInverse():List<LessonWithTeachers>

    @Query("SELECT name FROM teacher_table WHERE rang = 'Доктор наук'")
    fun getDoctors():List<String>
}