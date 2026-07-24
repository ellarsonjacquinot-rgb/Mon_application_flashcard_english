package com.example.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.viewmodel.MainViewModel
import com.example.ui.screens.DeckScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SearchExploreScreen
import com.example.ui.screens.SpeakingLabScreen
import com.example.ui.screens.StatsProgressScreen

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Accueil", Icons.Default.Home)
    object Deck : Screen("deck", "Flashcards", Icons.Default.Style)
    object Search : Screen("search", "Rechercher", Icons.Default.Search)
    object SpeakingLab : Screen("speaking_lab?cardId={cardId}", "Expression", Icons.Default.Mic) {
        fun createRoute(cardId: Int? = null) = if (cardId != null) "speaking_lab?cardId=$cardId" else "speaking_lab"
    }
    object Quiz : Screen("quiz", "Quiz", Icons.Default.Quiz)
    object Stats : Screen("stats", "Progrès", Icons.Default.BarChart)
}

@Composable
fun AppNavigation(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Deck,
        Screen.Search,
        Screen.SpeakingLab,
        Screen.Quiz,
        Screen.Stats
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    val isSelected = currentRoute?.startsWith(screen.route.split("?")[0]) == true
                    NavigationBarItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    onNavigateToDeck = { level ->
                        viewModel.setNiveauFilter(level)
                        navController.navigate(Screen.Deck.route)
                    },
                    onNavigateToSpeakingLab = {
                        navController.navigate(Screen.SpeakingLab.createRoute())
                    },
                    onNavigateToQuiz = {
                        navController.navigate(Screen.Quiz.route)
                    }
                )
            }

            composable(Screen.Deck.route) {
                DeckScreen(
                    viewModel = viewModel,
                    onNavigateToSpeakingLabWithCard = { card ->
                        navController.navigate(Screen.SpeakingLab.createRoute(card.id))
                    }
                )
            }

            composable(Screen.Search.route) {
                SearchExploreScreen(
                    viewModel = viewModel,
                    onNavigateToSpeakingLabWithCard = { card ->
                        navController.navigate(Screen.SpeakingLab.createRoute(card.id))
                    }
                )
            }

            composable(
                route = Screen.SpeakingLab.route,
                arguments = listOf(
                    navArgument("cardId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) { backStackEntry ->
                val cardId = backStackEntry.arguments?.getInt("cardId")
                val safeCardId = if (cardId != null && cardId != -1) cardId else null
                SpeakingLabScreen(
                    viewModel = viewModel,
                    initialCardId = safeCardId
                )
            }

            composable(Screen.Quiz.route) {
                QuizScreen(viewModel = viewModel)
            }

            composable(Screen.Stats.route) {
                StatsProgressScreen(viewModel = viewModel)
            }
        }
    }
}
