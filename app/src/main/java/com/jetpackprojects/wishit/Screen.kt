package com.jetpackprojects.wishit

//class containing the route for navigation
//nobody can inherite from a sealed class
sealed class Screen(val route:String) {
  object HomeScreen:Screen("home_screen")
  object AddScreen:Screen("add_screen")
}