package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertCountrie(noteEntity: Country)

    @Query("SELECT * FROM countries")
    fun pagingSource(): PagingSource<Int, Country>

    @Query("DELETE FROM countries")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Country>)

    @Query("SELECT * FROM countries WHERE name_en LIKE :query")
    fun pagingSource(query: String): PagingSource<Int, Country>

}