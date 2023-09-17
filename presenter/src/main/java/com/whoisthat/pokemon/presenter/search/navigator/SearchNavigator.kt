package com.whoisthat.pokemon.presenter.search.navigator

sealed class SearchNavigator {
    data class NavigateToDetailScreen(val id: String): SearchNavigator()
}