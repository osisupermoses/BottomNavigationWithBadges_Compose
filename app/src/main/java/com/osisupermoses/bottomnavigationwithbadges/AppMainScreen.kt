package com.osisupermoses.bottomnavigationwithbadges

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.osisupermoses.bottomnavigationwithbadges.bottom_nav.components.BottomNavigationBar
import com.osisupermoses.bottomnavigationwithbadges.bottom_nav.getBottomNavItems
import com.osisupermoses.bottomnavigationwithbadges.nav_drawer.components.Drawer
import com.osisupermoses.bottomnavigationwithbadges.nav_drawer.components.TopBar
import com.osisupermoses.bottomnavigationwithbadges.util.Screen
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AppMainScreen() {
    val navController = rememberNavController()

    val backStackEntry = navController.currentBackStackEntryAsState()
    val selectedScreen = backStackEntry.value?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = if (selectedScreen == Screen.Home.route) Screen.Home.drawerTitle
                else if (selectedScreen == Screen.Notifications.route) Screen.Notifications.drawerTitle
                else Screen.Settings.drawerTitle,
                buttonIcon = Icons.Default.Menu){ openDrawer() }
        },
        bottomBar = {
            BottomNavigationBar(
                items = getBottomNavItems(),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        //pop up to the start destination of the graph to avoid building
                        //a large stack of destinations on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when reselecting
                        // the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) {
        Navigation(navController = navController)

        Surface(color = MaterialTheme.colors.background) {

            ModalDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    Drawer(
                        onDestinationClicked = { route ->
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            ) {
                Navigation(
                    navController = navController,
                    openDrawer = { openDrawer() }
                )
            }
        }
    }

}