package org.idnp.jetpackpagingsample.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.idnp.jetpackpagingsample.entities.Countrie
import org.idnp.jetpackpagingsample.model.CountrieRepository

class CountrieViewModel : ViewModel() {
    private val userRepository: CountrieRepository = CountrieRepository()

    fun items(): Flow<PagingData<Countrie>> {

        val pager = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 3)
        ) {
            CountriePagingSource(userRepository)
        }.flow.cachedIn(viewModelScope)

        return pager

    }

}