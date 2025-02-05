package com.edu.wiet_admin.common.presentation.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edu.wiet_admin.common.presentation.scaffold.top_bar.WietTopAppBar
import com.edu.wiet_admin.navigation.AppNavGraph

@Composable
fun AppScaffold(
    startDestination: String
) {

    val navController = rememberNavController()
    val currDest = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        topBar = {
            WietTopAppBar(
                navController = navController,
                currDest = currDest
            )
        },
        bottomBar = {

        },
    ) { paddingValues ->
        AppNavGraph(
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues),
            navController = navController
        )
    }
}