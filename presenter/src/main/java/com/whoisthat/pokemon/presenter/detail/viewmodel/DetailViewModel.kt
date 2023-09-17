package com.whoisthat.pokemon.presenter.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whoisthat.pokemon.core.interactors.get_pokemon_detail_by_id.GetPokemonDetailByIdInteractor
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.presenter.detail.navigator.DetailNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val getPokemonDetailById: GetPokemonDetailByIdInteractor,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _pokemonState: MutableStateFlow<Pokemon> = MutableStateFlow(Pokemon())
    val pokemonState: StateFlow<Pokemon>
        get() = _pokemonState.asStateFlow()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), Pokemon())

    private val _navigator: MutableSharedFlow<DetailNavigator> = MutableSharedFlow()
    val navigator: SharedFlow<DetailNavigator>
        get() = _navigator.asSharedFlow()
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _loadingStatus: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingStatus: SharedFlow<Boolean>
        get() = _loadingStatus.asSharedFlow()
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _errorState: MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorState: SharedFlow<Throwable>
        get() = _errorState.asSharedFlow()
            .shareIn(viewModelScope, SharingStarted.Eagerly)

    fun getPokemonDetail(pokemonId: String?) {
        viewModelScope.launch(dispatcherProvider.main) {
            getPokemonDetailById(pokemonId ?: "err#2")
                .flowOn(dispatcherProvider.main)
                .collect {
                    Timber.i("Results are ${it.getOrNull()}")
                    if (it.isSuccess) {
                        _pokemonState.emit(it.getOrNull() ?: Pokemon())
                        _loadingStatus.emit(false)
                    } else if (it.isFailure) {
                        _errorState.emit(it.exceptionOrNull() ?: Throwable())
                        _loadingStatus.emit(false)
                    } else {
                        _loadingStatus.emit(true)
                    }
                }
        }
    }

    fun setLoadingStatus(status: Boolean = false){
        viewModelScope.launch(dispatcherProvider.main) {
            _loadingStatus.emit(status)
        }
    }

    fun navigateTo(navigator: DetailNavigator?) {
        viewModelScope.launch(dispatcherProvider.main) {
            _navigator.emit(navigator ?: DetailNavigator.Initial)
        }
    }
}