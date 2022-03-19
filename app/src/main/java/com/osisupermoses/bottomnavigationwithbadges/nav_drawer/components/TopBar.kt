package com.osisupermoses.bottomnavigationwithbadges.nav_drawer.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.osisupermoses.bottomnavigationwithbadges.util.Screen


private val screens = listOf(
    Screen.Home,
    Screen.Notifications,
    Screen.Settings
)

@Composable
fun TopBar(
    title: String = "Home",
    buttonIcon: ImageVector,
    onButtonClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}