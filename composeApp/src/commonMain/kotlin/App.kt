import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import navigation.AppNavHost
import navigation.TopLevelDestination
import org.koin.compose.KoinContext
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun App() {

    val appState = rememberAppState()

    MaterialTheme {
        KoinContext {

            Scaffold(
                bottomBar = {
                    BottomBar(
                        currentDestination = appState.currentDestination,
                        onTopLevelDestinationClicked = appState::navigateToTopLevelDestination
                    )
                }
            ) {
                AppNavHost(
                    navController = appState.navController,
                    modifier = Modifier.padding(it),
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    currentDestination: NavDestination?,
    onTopLevelDestinationClicked: (TopLevelDestination) -> Unit,
) {
    BottomNavigation {
        for (destination in TopLevelDestination.entries) {
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onTopLevelDestinationClicked(destination)
                },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = destination.getTitle())
                }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.equals(destination.route, true) ?: false
    } ?: false