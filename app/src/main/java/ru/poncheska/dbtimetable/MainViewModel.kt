package ru.poncheska.dbtimetable

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.poncheska.dbtimetable.database.TimetableDatabase

class MainViewModel(
    val database: TimetableDatabase,
    application: Application
) :AndroidViewModel(application){


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val textOutput = MutableLiveData<String>()


    init {
        textOutput.value = ""
    }

    fun onClickButton1(){
        uiScope.launch {
            val format = withContext(Dispatchers.IO){
                var groups = database.groupDao.getGroups()
                var groupsStr = groups.map { it.groupId.toString()}
                groupsStr
            }
            textOutput.value += "Запрос 1:\nId групп в таблице: $format\n\n"
        }
    }

    fun onClickButton2(){
        uiScope.launch {
            val groupNum = 1L
            val format = withContext(Dispatchers.IO){
                var lessons = database.groupDao.getLessonQuery(groupNum)
                var lessonsStr = lessons.map { "${it.name} ${it.room}" }
                lessonsStr
            }
            textOutput.value += "Запрос 2:\nНабор пар с кабинетами из расписания для группы $groupNum: $format\n\n"
        }
    }
    fun onClickButton3(){
        uiScope.launch {
            val format = withContext(Dispatchers.IO){
                var teachers = database.lessonDao.getTeacherQuerry()
                var teachersStr = teachers.map { "${it.name} : ${it.list}   " }
                teachersStr
            }
            textOutput.value += "Запрос 3:\nПреподователи и их пары  : $format\n\n"
        }
    }
    fun onClickButton4(){
        uiScope.launch {
            val format = withContext(Dispatchers.IO){
                var lessons = database.teacherDao.getTeacherQuerryInverse()
                var lessonsStr = lessons.map { "${it.name} : ${it.list}   " }
                lessonsStr
            }
            textOutput.value += "Запрос 4:\nПары и ведущие их преподаватели : $format\n\n"
        }
    }
    fun onClickButton5(){
        uiScope.launch {
            val format = withContext(Dispatchers.IO){
                val groupNum = 1L
                var lastPair = database.groupDao.getFirstLessonInfo(groupNum)
                var lastPairStr = "У группы с id = $groupNum ${if(lastPair) "последняя пара будет" else "последней пары не будет"}"
                lastPairStr

            }
            textOutput.value += "Запрос 5:\nБудет ли последняя пара? : $format\n\n"
        }
    }
    fun onClickButton6(){
        uiScope.launch {
            val format = withContext(Dispatchers.IO){

                var doctors = database.teacherDao.getDoctors()
                doctors
            }
            textOutput.value += "Запрос 6:\n Доктора наук : $format\n\n"
        }
    }
    fun onClickButton7(){
        uiScope.launch {
            val format = withContext(Dispatchers.IO){
                var groups = database.groupDao.getMaxGroup()
                var groupsStr = groups.map { "у группы id = ${it.groupId} с количеством ${it.numberOfStudents}" +
                        " последней парой будет ${it.name}" }
                groupsStr
            }
            textOutput.value += "Запрос 7:\n Последние пары у групп с наибольшим количеством учащихся: $format\n\n"
        }
    }

    fun onClickButtonClear(){
        textOutput.value = ""
    }
}