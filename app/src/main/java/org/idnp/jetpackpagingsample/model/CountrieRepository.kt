package org.idnp.jetpackpagingsample.model

import android.util.Log
import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.idnp.jetpackpagingsample.database.CountrieDatabase
import org.idnp.jetpackpagingsample.entities.Countrie

class CountrieRepository(
    private val appDatabase: CountrieDatabase
) {
    fun getUsers(nextPageNumber: Int): List<Countrie> {

        Log.d("nextPageNumber:", nextPageNumber.toString())

        val users = arrayListOf<Countrie>()
        var user: Countrie
        val start: Int = 100 * nextPageNumber
        val end = start + 20

        for (i in start..end) {
            user = Countrie(
                countrieId = i,
                name_en = "FirstName " + i,
                name_es = "SecondName " + i,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                0
            );
            users.add(user)
        }

        return users
    }

     suspend fun insertAll(studentsEntity: List<Countrie>) {
         appDatabase.countrieDao().insertAll(studentsEntity)
     }

    suspend fun insertCountrie(studentsEntity: Countrie) {
        appDatabase.countrieDao().insertCountrie(studentsEntity)
    }
     fun pagingSource(query: String): PagingSource<Int, Countrie> {
         return appDatabase.countrieDao().pagingSource()
     }

     fun getAll(): Flow<List<Countrie>>{
         return appDatabase.countrieDao().getAll()
     }
     suspend fun clearAll() {
         return appDatabase.countrieDao().clearAll()
     }

}

//data class ResponseUser       (val users: List<User>, val nextPageNumber: Int)