package com.martacalo.letsplay.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martacalo.letsplay.data.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            searchRepository.refreshGames()
        }
    }

}
