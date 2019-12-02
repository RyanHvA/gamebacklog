package com.example.gamebacklog.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GamebacklogRepository
import com.example.gamebacklog.model.Gamebacklog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gamebacklogRepository = GamebacklogRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val gamebacklog: LiveData<List<Gamebacklog>> = gamebacklogRepository.getGamebacklog()

    fun insertGamebacklog(gamebacklog: Gamebacklog) {
        ioScope.launch {
            gamebacklogRepository.insertGamebacklog(gamebacklog)
        }
    }

    fun deleteGamebacklog(gamebacklog: Gamebacklog) {
        ioScope.launch {
            gamebacklogRepository.deleteGamebacklog(gamebacklog)
        }
    }

    fun deleteAllGamebacklog() {
        ioScope.launch {
            gamebacklogRepository.deleteAllGamebacklog()
        }
    }

}