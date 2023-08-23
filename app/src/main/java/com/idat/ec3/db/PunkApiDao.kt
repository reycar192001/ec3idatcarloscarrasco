package com.idat.ec3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idat.ec3.PunkApi

@Dao
interface PunkApiDao {

    @Query("SELECT * FROM punkapi")
    fun getFavorites(): List<PunkApi>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(punkApi: PunkApi)
}