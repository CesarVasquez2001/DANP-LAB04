package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import org.idnp.jetpackpagingsample.database.CountryDatabase
import org.idnp.jetpackpagingsample.entities.Country

class CountryRepository(
    private val appDatabase: CountryDatabase
) {


    val browsecountry : PagingSource<Int, Country> = appDatabase.countrieDao().pagingSource()

    suspend fun insertCountrie(studentsEntity: Country) {
        appDatabase.countrieDao().insertCountrie(studentsEntity)
    }

     suspend fun clearAll() {
         return appDatabase.countrieDao().clearAll()
     }

}

