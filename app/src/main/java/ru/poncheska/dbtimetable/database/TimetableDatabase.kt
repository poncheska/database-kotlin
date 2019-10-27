package ru.poncheska.dbtimetable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.*
import ru.poncheska.dbtimetable.database.daos.*
import ru.poncheska.dbtimetable.database.entities.*

@Database(entities = [Timetable::class, Teacher::class, Link::class, Lesson::class, Group::class],
    version = 1, exportSchema = false)
abstract class TimetableDatabase : RoomDatabase() {


    abstract val groupDao:GroupDao

    abstract val lessonDao:LessonDao

    abstract val linkDao:LinkDao

    abstract val teacherDao:TeacherDao

    abstract val timetableDao:TimetableDao

    companion object {

        @Volatile
        private var INSTANCE: TimetableDatabase? = null

        fun getInstance(context: Context): TimetableDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TimetableDatabase::class.java,
                        "timetable_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    initTable(instance)
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }

        fun initTable(database: TimetableDatabase){
            var viewModelJob = Job()
            val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

            uiScope.launch {
                withContext(Dispatchers.IO) {
                    if(database.groupDao.getGroups().isEmpty()) {
                        database.teacherDao.insert(
                            Teacher(
                                teacherId = 1, name = "Магистр Йода",
                                rang = "Доктор наук", isWorking = true
                            )
                        )
                        database.teacherDao.insert(
                            Teacher(
                                teacherId = 2, name = "Мастер Сплинтер",
                                rang = "Доцент", isWorking = true
                            )
                        )
                        database.teacherDao.insert(
                            Teacher(
                                teacherId = 3, name = "Совунья",
                                rang = "Доктор наук", isWorking = true
                            )
                        )
                        database.teacherDao.insert(
                            Teacher(
                                teacherId = 4, name = "Джимми Нейтрон",
                                rang = "Ассистент", isWorking = false
                            )
                        )


                        database.linkDao.insert(Link(lessonId = 1, teacherId = 1))
                        database.linkDao.insert(Link(lessonId = 2, teacherId = 2))
                        database.linkDao.insert(Link(lessonId = 3, teacherId = 3))
                        database.linkDao.insert(Link(lessonId = 4, teacherId = 3))
                        database.linkDao.insert(Link(lessonId = 1, teacherId = 4))


                        database.lessonDao.insert(Lesson(lessonId = 1, name = "Математический анализ", room = "228"))
                        database.lessonDao.insert(Lesson(lessonId = 2, name = "Алгебра", room = "225"))
                        database.lessonDao.insert(Lesson(lessonId = 3, name = "Геометрия", room = "222"))
                        database.lessonDao.insert(Lesson(lessonId = 4, name = "Методы оптимизации", room = "224"))


                        database.timetableDao.insert(
                            Timetable(
                                timetableId = 1, lesson1 = 1, lesson2 = 1,
                                lesson3 = 2, lesson4 = 2, lesson5 = 4
                            )
                        )
                        database.timetableDao.insert(
                            Timetable(
                                timetableId = 2, lesson1 = 3, lesson2 = 3,
                                lesson3 = 4, lesson4 = 1, lesson5 = 2
                            )
                        )


                        database.groupDao.insert(Group(numberOfStudents = 12, timetableId = 1))
                        database.groupDao.insert(Group(numberOfStudents = 11, timetableId = 2))
                        database.groupDao.insert(Group(numberOfStudents = 15, timetableId = 1))
                        database.groupDao.insert(Group(numberOfStudents = 13, timetableId = 2))
                    }
                }
            }
        }
    }

}