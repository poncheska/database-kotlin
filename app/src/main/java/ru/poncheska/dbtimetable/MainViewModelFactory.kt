package ru.poncheska.dbtimetable

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.poncheska.dbtimetable.database.TimetableDatabase

class MainViewModelFactory(
    private val database: TimetableDatabase,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
              return MainViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}