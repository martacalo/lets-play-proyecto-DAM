package com.martacalo.letsplay.ui.search

import com.martacalo.letsplay.core.ui.BaseViewModel
import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.data.SearchRepository
import com.martacalo.letsplay.data.remote.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : BaseViewModel<SearchViewEvent, SearchViewState>() {

    override fun createInitialState(): SearchViewState = SearchViewState()

    override fun handleEvent(event: SearchViewEvent): Flow<StateReducer<SearchViewState>> {
        val initFlow = flowOf(event)
            .filterIsInstance<SearchViewEvent.OnInit>()
            .map { searchRepository.refreshGames() }
            .map { SearchStateReducer.Init }

        val searchFlow = flowOf(event)
            .filterIsInstance<SearchViewEvent.OnSearch>()
            .flatMapConcat {
                searchRepository
                    .search(query = it.query)
                    .map { response ->
                        when (response) {
                            is ResponseResult.Success ->
                                SearchStateReducer.Search(gamesList = response.data)
                            is ResponseResult.Error ->
                                SearchStateReducer.Search(errorMessage = response.message)
                        }
                    }
            }

        return merge(
            initFlow,
            searchFlow,
        )
    }

    fun init() {
        produceEvent(SearchViewEvent.OnInit)
    }


}
