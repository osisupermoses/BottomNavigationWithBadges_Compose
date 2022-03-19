package com.osisupermoses.bottomnavigationwithbadges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.osisupermoses.bottomnavigationwithbadges.bottom_nav.components.ChatScreen
import com.osisupermoses.bottomnavigationwithbadges.bottom_nav.components.HomeScreen
import com.osisupermoses.bottomnavigationwithbadges.bottom_nav.components.SettingsScreen
import com.osisupermoses.bottomnavigationwithbadges.ui.theme.BottomNavigationWithBadgesTheme
import com.osisupermoses.bottomnavigationwithbadges.util.Screen

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationWithBadgesTheme {
                AppMainScreen()
            }
        }
    }
}

@Composable
fun Navigation(openDrawer: () -> Unit = {}, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                openDrawer = { openDrawer() }
            )
        }
        composable(Screen.Notifications.route) {
            ChatScreen(
                openDrawer = { openDrawer() }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController
            )
        }
    }
}