package com.example.gamebacklog.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Gamebacklog

class GamebacklogRepository(context: Context) {

    private val gamebacklogDao: GamebacklogDao

    init {
        val gamebacklogRoomDatabase =
            GamebacklogRoomDatabase.getDatabase(context)
        gamebacklogDao = gamebacklogRoomDatabase!!.gamebacklogDao()
    }

    suspend fun insertGamebacklog(gamebacklog: Gamebacklog) {
        gamebacklogDao.insertGamebacklog(gamebacklog)
    }

    suspend fun updateGamebacklog(gamebacklog: Gamebacklog) {
        gamebacklogDao.updateGamebacklog(gamebacklog)
    }

    suspend fun deleteGamebacklog(gamebacklog: Gamebacklog) {
        gamebacklogDao.deleteGamebacklog(gamebacklog)
    }

    fun getGamebacklog(): LiveData<List<Gamebacklog>> {
        return gamebacklogDao.getGamebacklog()
    }

    fun deleteAllGamebacklog() {
        gamebacklogDao.deleteAllGamebacklog()
    }

}
