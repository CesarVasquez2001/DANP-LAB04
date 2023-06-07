package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Countrie
 import kotlinx.coroutines.flow.Flow
@Dao
interface CountrieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Countrie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountrie(noteEntity: Countrie)

    @Query("SELECT * FROM countries")
    fun pagingSource(): PagingSource<Int, Countrie>

    @Query("SELECT * FROM countries")
    fun getAll(): Flow<List<Countrie>>

    @Query("DELETE FROM countries")
    suspend fun clearAll()
 }