package com.idat.ec3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idat.ec3.PunkApi


@Database(entities = [PunkApi::class], version = 1)
abstract class PunkApiDataBase : RoomDatabase() {
    abstract fun punkApiDao(): PunkApiDao

    companion object {
        @Volatile
        private var instance: PunkApiDataBase? = null

        fun getDatabase(context: Context): PunkApiDataBase {
            if (instance == null){
                synchronized(this){
                    instance=buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context): PunkApiDataBase? {
            return Room.databaseBuilder(
                context.applicationContext,
                PunkApiDataBase::class.java,
                "punkapi_database"
            ).build()

        }
    }
}