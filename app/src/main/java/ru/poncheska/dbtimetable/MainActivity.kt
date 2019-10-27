package ru.poncheska.dbtimetable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ru.poncheska.dbtimetable.database.TimetableDatabase
import ru.poncheska.dbtimetable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = TimetableDatabase.getInstance(application)
        val viewModel = ViewModelProviders.of(this,
            MainViewModelFactory(database,application)).get(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
