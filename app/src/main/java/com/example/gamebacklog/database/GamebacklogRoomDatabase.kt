package com.example.gamebacklog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamebacklog.model.Gamebacklog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Gamebacklog::class], version = 1, exportSchema = false)
abstract class GamebacklogRoomDatabase : RoomDatabase() {

    abstract fun gamebacklogDao(): GamebacklogDao

    companion object {
        private const val DATABASE_NAME = "GAMEBACKLOG_DATABASE"

        @Volatile
        private var INSTANCE: GamebacklogRoomDatabase? = null

        fun getDatabase(context: Context): GamebacklogRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(GamebacklogRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GamebacklogRoomDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}
