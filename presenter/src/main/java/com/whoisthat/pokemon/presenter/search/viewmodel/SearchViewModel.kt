package com.whoisthat.pokemon.presenter.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.whoisthat.pokemon.core.interactors.get_cards_by_search.GetCardsPagingBySearchInteractor
import com.whoisthat.pokemon.core.interactors.get_simple_data.GetSimpleDataInteractor
import com.whoisthat.pokemon.domain.domain.NetworkCardsQueryParams
import com.whoisthat.pokemon.domain.domain.Pokemon
import com.whoisthat.pokemon.domain.source.DispatcherProvider
import com.whoisthat.pokemon.presenter.search.navigator.SearchNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getSimpleDataInteractor: GetSimpleDataInteractor,
    val getCardsPagingBySearch: GetCardsPagingBySearchInteractor,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    var searchQueryState: NetworkCardsQueryParams = NetworkCardsQueryParams()

    private val _pokemonList: MutableStateFlow<List<Pokemon>> = MutableStateFlow(listOf())
    val pokemonList: StateFlow<List<Pokemon>> get() = _pokemonList.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    private val _pokemonPagingState: MutableStateFlow<PagingData<Pokemon>> = MutableStateFlow(PagingData.empty())
    val pokemonPagingState: StateFlow<PagingData<Pokemon>> get()= _pokemonPagingState.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PagingData.empty())

    private val _loadingStatus: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingStatus: SharedFlow<Boolean> get() = _loadingStatus.asSharedFlow()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    private val _errorState: MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorState: SharedFlow<Throwable> get() = _errorState.asSharedFlow()
        .shareIn(viewModelScope, SharingStarted.Eagerly)

    private val _navigation: MutableSharedFlow<SearchNavigator> = MutableSharedFlow()
    val navigation: SharedFlow<SearchNavigator> get()= _navigation.asSharedFlow()
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())

    fun searchPagingPokemonsWithParams(){
        viewModelScope.launch(dispatcherProvider.main) {
            getCardsPagingBySearch(searchQueryState)
                .flowOn(dispatcherProvider.io)
                .cachedIn(viewModelScope)
                .catch {
                    Timber.i("Data Response Paging Error: ${it.message}")
                    _errorState.emit(it)
                }
                .collect { data ->
                    Timber.i("Data Response Paging: $data")
                    _pokemonPagingState.emit(data)
                }
        }
    }

    fun queryHandler(query: String?){
        searchQueryState = NetworkCardsQueryParams(
            query = "name:$query*",
            page = 1,
            orderBy = NetworkCardsQueryParams.OrderBy.NUMBER
        )
        viewModelScope.launch{
            searchPagingPokemonsWithParams()
        }
    }

    fun navigateTo(navigate: SearchNavigator) {
        viewModelScope.launch { _navigation.emit(navigate) }
    }


    /* Network Early Adaptation Test
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
     */

}