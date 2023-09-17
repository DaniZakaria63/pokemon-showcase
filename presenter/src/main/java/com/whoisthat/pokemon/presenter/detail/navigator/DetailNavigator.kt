package com.whoisthat.pokemon.presenter.detail.navigator

sealed class DetailNavigator {
    data object NavigateToSearchScreen: DetailNavigator()
    data object Initial: DetailNavigator()
}