package com.cvirn.mototest.navigation

import com.cvirn.mototest.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.ic_home_active, "home")
    object Chat : BottomNavItem("Chat", R.drawable.ic_chat, "chat")
    object Biker : BottomNavItem("Biker", R.drawable.ic_biker, "biker")
    object Map : BottomNavItem("Map", R.drawable.ic_maps, "map")
    object Menu : BottomNavItem("Menu", R.drawable.ic_menu, "menu")
}
