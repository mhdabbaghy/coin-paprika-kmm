import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coin_detail.CoinDetailRoute
import coin_list.CoinListRoute
import coin_list.CoinListViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    MaterialTheme {
        KoinContext {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "coin_list",
            ) {
                composable(
                    route = "coin_list",
                ) {
                    val viewModel: CoinListViewModel = koinViewModel()
                    CoinListRoute(
                        viewModel = viewModel,
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
    }
}