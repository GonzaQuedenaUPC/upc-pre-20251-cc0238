package pe.edu.upc.todocompose.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.todocompose.domain.model.Task

@Preview
@Composable
fun Home() {
    val navController = rememberNavController()
    val tasks = emptyList<Task>()

    NavHost(
        navController = navController,
        startDestination = Routes.TaskList.route
    ) {
        composable(Routes.TaskList.route) {
            TaskList(tasks = tasks) {
                navController.navigate(Routes.TaskDetail.route)
            }
        }

        composable(Routes.TaskDetail.route) {
            TaskDetail { task ->

                navController.popBackStack()
            }
        }
    }
}

sealed class Routes(val route: String) {
    data object TaskList : Routes(route = "TaskList")
    data object TaskDetail : Routes(route = "TaskDetail")
}