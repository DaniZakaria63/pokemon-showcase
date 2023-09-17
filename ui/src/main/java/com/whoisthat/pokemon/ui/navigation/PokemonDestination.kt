package com.whoisthat.pokemon.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.whoisthat.pokemon.domain.navigation.NavTarget
import com.whoisthat.pokemon.ui.navigation.base.BaseDestination

object SearchDestination : BaseDestination {
    override val icon: ImageVector
        get() = Icons.Filled.List
    override val route: String
        get() = NavTarget.SEARCH.label
}

object DetailDestination: BaseDestination {
    override val icon: ImageVector
        get() = Icons.Filled.Newspaper
    override val route: String
        get() = NavTarget.DETAIL.label

    val detailIdArgs = "pokemon_id"
    val routeWithArgs ="$route/{$detailIdArgs}"

    val arguments = listOf(
        navArgument(detailIdArgs) {
            type = NavType.StringType
            defaultValue = ""
        }
    )
    val deepLink = listOf(
        navDeepLink { uriPattern = "pokemon://$route/{$detailIdArgs}" }
    )
}