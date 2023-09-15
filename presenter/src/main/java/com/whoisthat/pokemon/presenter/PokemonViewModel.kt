package com.whoisthat.pokemon.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whoisthat.pokemon.core.interactors.get_simple_data.GetSimpleDataInteractor
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val getSimpleDataInteractor: GetSimpleDataInteractor,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _loadingStatus: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingStatus: SharedFlow<Boolean> get() = _loadingStatus.asSharedFlow()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _pokemonList: MutableStateFlow<List<Pokemon>> = MutableStateFlow(listOf())
    val pokemonList: StateFlow<List<Pokemon>> get() = _pokemonList.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    val _errorState: MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorState: SharedFlow<Throwable> get() = _errorState.asSharedFlow()
        .shareIn(viewModelScope, SharingStarted.Eagerly)

    fun getSimpleData() {
        viewModelScope.launch(dispatcherProvider.main) {
            getSimpleDataInteractor()
                .flowOn(dispatcherProvider.io)
                .collect{
                    if(it.isSuccess){
                        _pokemonList.emit(it.getOrNull()?: listOf())
                        _loadingStatus.emit(false)
                    }else if(it.isFailure){
                        _errorState.emit(it.exceptionOrNull()?: Throwable())
                        _loadingStatus.emit(false)
                    }else{
                        _loadingStatus.emit(true)
                    }
                }
        }
    }
}