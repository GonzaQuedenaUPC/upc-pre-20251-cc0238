package pe.edu.upc.mealscompose.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.mealscompose.domain.model.Category

@Preview
@Composable
fun Home() {
    val navController = rememberNavController()

    val selectedCategory = remember {
        mutableStateOf<Category?>(null)
    }

    val navigationItems = listOf(
        NavigationItem(Icons.Default.Home, "Categories", "categories"),
        NavigationItem(Icons.Default.Favorite, "Favorites", "favorites"),

        )

    val selectedIndex = remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex.value == index,
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        onClick = {
                            selectedIndex.value = index
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(item.title)
                        },

                        )
                }
            }
        }

    ) { padding ->


        NavHost(
            navController,
            startDestination = "categories",
            modifier = Modifier.padding(padding)
        ) {

            composable("categories") {
                CategoryListView(onTap = { category ->
                    selectedCategory.value = category
                    navController.navigate("meals")
                })
            }

            composable("meals") {
                selectedCategory.value?.name?.let { it ->
                    MealListView(category = it)
                }
            }
            composable("favorites") {
                Text("Favorites")
            }
        }
    }
}

data class NavigationItem(
    val icon: ImageVector,
    val title: String,
    val route: String
)