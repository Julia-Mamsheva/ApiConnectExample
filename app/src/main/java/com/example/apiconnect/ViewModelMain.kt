package com.example.apiconnect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiconnect.api.Repository
import com.example.apiconnect.model.RickAndMorty
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.apiconnect.model.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModelMain(private val repository: Repository): ViewModel() {
    private val _rickAndMorty = MutableStateFlow<List<Result>>(emptyList())
    val rickAndMorty = _rickAndMorty.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            repository.getCharacter().collect { result ->
                when(result) {
                    is com.example.apiconnect.api.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is com.example.apiconnect.api.Result.Success -> {
                        result.dta?.let { products ->
                            _rickAndMorty.update { products.results }
                        }
                    }
                }
            }
        }
    }
}