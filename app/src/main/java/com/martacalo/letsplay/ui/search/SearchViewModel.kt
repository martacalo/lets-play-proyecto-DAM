package com.martacalo.letsplay.ui.search

import com.martacalo.letsplay.core.ui.BaseViewModel
import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.core.ui.ViewEffect
import com.martacalo.letsplay.data.SearchRepository
import com.martacalo.letsplay.data.remote.ResponseResult
import com.martacalo.letsplay.ui.search.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : BaseViewModel<SearchViewIntent, SearchViewState>() {

    override fun createInitialState(): SearchViewState = SearchViewState()

    override fun handleIntent(intent: SearchViewIntent): Flow<StateReducer<SearchViewState>> {
        val searchFlow = flowOf(intent)
            .filterIsInstance<SearchViewIntent.OnSearch>()
            .distinctUntilChanged()
            .debounce(400)
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

        val selectGameFlow = flowOf(intent)
            .filterIsInstance<SearchViewIntent.OnSelectGame>()
            .debounce(400)
            .flatMapConcat {
                searchRepository.saveGame(it.game.id)
                flowOf(SearchStateReducer.SaveGame(it.game))
            }

        return merge(
            searchFlow,
            selectGameFlow,
        )
    }

    override fun getViewEffectFromStateReducer(reducer: StateReducer<SearchViewState>): ViewEffect? =
        when (reducer) {
            is SearchStateReducer.SaveGame -> SearchViewEffect.Toast(reducer.game.name)
            else -> null
        }

    fun search(query: String = "") {
        produceIntent(SearchViewIntent.OnSearch(query))
    }

    fun selectGame(game: Game) {
        produceIntent(SearchViewIntent.OnSelectGame(game))
    }

}
