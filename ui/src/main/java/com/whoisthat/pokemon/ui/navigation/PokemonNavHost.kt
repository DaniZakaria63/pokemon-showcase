package com.whoisthat.pokemon.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.whoisthat.pokemon.presenter.detail.navigator.DetailNavigator
import com.whoisthat.pokemon.presenter.search.navigator.SearchNavigator
import com.whoisthat.pokemon.ui.detail.DetailScreen
import com.whoisthat.pokemon.ui.search.SearchScreen
import timber.log.Timber


@Composable
fun PokemonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SearchDestination.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(easing = EaseIn)
            ) + fadeIn(animationSpec = tween(easing = LinearEasing))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(easing = EaseOut)
            ) + fadeOut(animationSpec = tween(easing = LinearEasing))
        },
        modifier = modifier
    ) {
        composable(
            route = SearchDestination.route
        ) {
            SearchScreen { navigator ->
                when (navigator) {
                    is SearchNavigator.NavigateToDetailScreen -> navController.navigateToDetail(
                        navigator.id
                    )

                    else -> Timber.e("How???")
                }
            }
        }
        composable(
            route = DetailDestination.routeWithArgs,
            arguments = DetailDestination.arguments,
            deepLinks = DetailDestination.deepLink
        ) { navBackStackEntry ->
            val pokemonId = navBackStackEntry.arguments?.getString(DetailDestination.detailIdArgs)
            DetailScreen(pokemonId) { navigator ->
                when (navigator) {
                    is DetailNavigator.NavigateToSearchScreen -> navController.popBackStack()
                    else -> Timber.e("How could???")
                }
            }
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

private fun NavHostController.navigateToDetail(id: String) =
    this.navigateSingleTopTo("${DetailDestination.route}/$id")
