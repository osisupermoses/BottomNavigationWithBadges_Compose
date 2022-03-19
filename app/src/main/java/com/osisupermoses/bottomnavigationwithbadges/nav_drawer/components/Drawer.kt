package com.osisupermoses.bottomnavigationwithbadges.nav_drawer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.osisupermoses.bottomnavigationwithbadges.R
import com.osisupermoses.bottomnavigationwithbadges.util.Screen


private val screens = listOf(
        Screen.Home,
        Screen.Notifications,
        Screen.Settings
    )


@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_baseline_android_24),
            contentDescription = "App icon"
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(10.dp))
            Text(
                text = screen.drawerTitle,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .clickable {
                        onDestinationClicked(screen.route)
                    }
            )
        }
    }
}