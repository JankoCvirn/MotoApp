package com.cvirn.mototest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cvirn.mototest.component.TopBar
import com.cvirn.mototest.component.createGradientBrush
import com.cvirn.mototest.navigation.BottomNavItem
import com.cvirn.mototest.navigation.BottomNavigation
import com.cvirn.mototest.screen.BikerScreen
import com.cvirn.mototest.screen.ChatScreen
import com.cvirn.mototest.screen.HomeScreen
import com.cvirn.mototest.screen.MapScreen
import com.cvirn.mototest.screen.MenuScreen
import com.cvirn.mototest.ui.theme.MotoTestTheme
import com.cvirn.mototest.ui.theme.endGradient
import com.cvirn.mototest.ui.theme.startGradient

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotoTestTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopBar(onBack = { }, title = "Services")
                    },
                    bottomBar = { BottomNavigation(navController = navController) },

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = createGradientBrush(
                                    colors = listOf(startGradient, endGradient),
                                    isVertical = true,
                                ),
                            ).padding(8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Chat.screen_route) {
            ChatScreen()
        }
        composable(BottomNavItem.Biker.screen_route) {
            BikerScreen()
        }
        composable(BottomNavItem.Map.screen_route) {
            MapScreen()
        }
        composable(BottomNavItem.Menu.screen_route) {
            MenuScreen()
        }
    }
}
