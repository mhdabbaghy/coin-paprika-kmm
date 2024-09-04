package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import coinpaprika.composeapp.generated.resources.Res
import coinpaprika.composeapp.generated.resources.title_coins
import coinpaprika.composeapp.generated.resources.title_home
import org.jetbrains.compose.resources.stringResource

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {


    HOME(
        route = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    COINS(
        route = "Coins",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
    );


    @Composable
    fun getTitle(): String = when (this) {
        HOME -> stringResource(Res.string.title_home)
        COINS -> stringResource(Res.string.title_coins)
    }


}