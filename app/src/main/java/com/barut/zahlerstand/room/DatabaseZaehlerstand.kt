package com.barut.zahlerstand.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.barut.zahlerstand.model.MainFragmentModel

@Database(entities = [MainFragmentModel::class], version = 1)
abstract class DatabaseZaehlerstand : RoomDatabase() {

    abstract fun dao() : DataBaseDAO

    companion object{

        @Volatile private var instance : DatabaseZaehlerstand? = null

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: makeDataBase(context).also {
                instance = it
            }
        }

        private fun makeDataBase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseZaehlerstand::class.java,
            "Database"
        ).build()
    }
}












