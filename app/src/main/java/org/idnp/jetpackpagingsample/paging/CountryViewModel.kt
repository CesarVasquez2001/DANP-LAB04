package org.idnp.jetpackpagingsample.paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.idnp.jetpackpagingsample.database.CountryDatabase
import org.idnp.jetpackpagingsample.entities.Country
import org.idnp.jetpackpagingsample.model.CountryRepository

class CountryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CountryRepository

    init {
        val dataBase = CountryDatabase.getInstance(application)
        repository = CountryRepository(dataBase)
    }

    fun items(): Flow<PagingData<Country>> {

        val pager = Pager(
            PagingConfig(
                pageSize = 20,
                initialLoadSize = 20 * 5,
                enablePlaceholders = false,
                prefetchDistance = 3
            )
        ) {
            repository.browsecountry
        }.flow.cachedIn(viewModelScope)

        return pager

    }

}