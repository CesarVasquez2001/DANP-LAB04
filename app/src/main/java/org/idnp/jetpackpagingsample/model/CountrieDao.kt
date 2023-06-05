package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Countrie

@Dao
interface CountrieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Countrie>)

    @Query("SELECT * FROM countries")
    fun pagingSource(query: String): PagingSource<Int, Countrie>

    @Query("DELETE FROM countries")
    suspend fun clearAll()
}