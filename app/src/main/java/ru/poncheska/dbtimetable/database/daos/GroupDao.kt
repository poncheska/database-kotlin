package ru.poncheska.dbtimetable.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.poncheska.dbtimetable.database.entities.Group


@Dao
interface GroupDao{
    @Insert
    fun insert(group: Group)

    @Delete
    fun delete(group: Group)

    @Query("SELECT * from group_table")
    fun getGroups():List<Group>

    data class LessonWithRoom(
        val name: String,
        val room: String
    )

    @Query("SELECT lesson_table.name, lesson_table.room FROM group_table INNER JOIN timetable_table ON" +
            " group_table.timetableId == timetable_table.timetableId INNER JOIN lesson_table ON" +
            " lesson_table.lessonId == timetable_table.lesson1 OR lesson_table.lessonId == timetable_table.lesson2 OR" +
            " lesson_table.lessonId == timetable_table.lesson3 OR lesson_table.lessonId == timetable_table.lesson4 OR" +
            " lesson_table.lessonId == timetable_table.lesson5 WHERE groupId = :groupId")
    fun getLessonQuery(groupId:Long): List<LessonWithRoom>


    @Query("SELECT sum(teacher_table.isWorking) AS bool FROM group_table INNER JOIN timetable_table ON" +
            " group_table.timetableId == timetable_table.timetableId INNER JOIN lesson_table ON " +
            " lesson_table.lessonId == timetable_table.lesson1 INNER JOIN link_table ON" +
            " lesson_table.lessonId == link_table.lessonId INNER JOIN teacher_table ON" +
            " link_table.teacherId == teacher_table.teacherId WHERE group_table.groupId = :groupId GROUP BY teacher_table.isWorking")
    fun getFirstLessonInfo(groupId: Long):Boolean


    data class GroupWithNum(
        val groupId: Long,
        val numberOfStudents:Int,
        val name:String
    )
    @Query("SELECT groupId, numberOfStudents, lesson_table.name, max(numberOfStudents) FROM group_table INNER JOIN timetable_table ON" +
            " group_table.timetableId == timetable_table.timetableId INNER JOIN lesson_table ON" +
            " lesson_table.lessonId == timetable_table.lesson5")
    fun getMaxGroup():List<GroupWithNum>
}