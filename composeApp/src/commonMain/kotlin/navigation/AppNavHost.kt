package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import feature.coin_detail.CoinDetailRoute
import feature.coin_list.CoinListRoute
import feature.home.HomeRoute

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestination.HOME.route,
        modifier = modifier,
    ) {

        composable(
            route = TopLevelDestination.HOME.route,
        ) {
            HomeRoute()
        }



        composable(
            route = TopLevelDestination.COINS.route,
        ) {
            CoinListRoute(
                onCoinClick = {
                    navController.navigate(route = "coin_detail/$it")
                }
            )
        }


        composable(
            route = "coin_detail/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                    nullable = false
                },
            )
        ) {

            val id = it.arguments!!.getString("id")!!

            CoinDetailRoute(
                onBackClick = { navController.navigateUp() },
                id = id,
            )

        }
    }
}