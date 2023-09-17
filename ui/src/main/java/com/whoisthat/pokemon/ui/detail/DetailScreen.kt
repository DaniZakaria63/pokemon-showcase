package com.whoisthat.pokemon.ui.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.whoisthat.pokemon.presenter.detail.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigatorCallback:()->Unit,
) {
}