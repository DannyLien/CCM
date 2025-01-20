package com.hank.ccm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Recorded::class), version = 1)
abstract class GuessRoomDatabase : RoomDatabase() {

    abstract fun recordedDao(): RecordedDao

    companion object {
        var instance: GuessRoomDatabase? = null
        fun getInstance(context: Context): GuessRoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    GuessRoomDatabase::class.java,
                    "guess.db"
                ).build()
            }
            return instance
        }
    }

}