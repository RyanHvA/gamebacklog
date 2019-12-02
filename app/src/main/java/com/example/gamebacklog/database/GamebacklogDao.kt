package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamebacklog.model.Gamebacklog


@Dao
interface GamebacklogDao {

    @Insert
    suspend fun insertGamebacklog(gamebacklog: Gamebacklog)

    @Update
    suspend fun updateGamebacklog(gamebacklog: Gamebacklog)

    @Delete
    suspend fun deleteGamebacklog(gamebacklog: Gamebacklog)

    @Query("SELECT * FROM Gamebacklog")
    fun getGamebacklog(): LiveData<List<Gamebacklog>>

    @Query("DELETE FROM Gamebacklog")
    fun deleteAllGamebacklog()

}
