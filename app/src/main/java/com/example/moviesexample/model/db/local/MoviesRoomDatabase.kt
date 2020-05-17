package com.example.moviesexample.model.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesexample.model.data.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesRoomDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesRoomDao

    companion object {

        @Volatile
        private var INSTANCE: MoviesRoomDatabase? = null

        fun getDatabase(context: Context): MoviesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesRoomDatabase::class.java,
                    "movies_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}