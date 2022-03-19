package com.osisupermoses.bottomnavigationwithbadges.util

sealed class Screen(val drawerTitle: String, val route: String) {
    object Home : Screen("Home", "home")
    object Notifications : Screen("Notifications", "chat")
    object Settings : Screen( "Settings", "settings")
}