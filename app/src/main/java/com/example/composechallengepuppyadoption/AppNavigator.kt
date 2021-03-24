package com.example.composechallengepuppyadoption

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composechallengepuppyadoption.data.Puppy
import com.example.composechallengepuppyadoption.data.listOfPuppies
import com.example.composechallengepuppyadoption.ui.PuppyDetailView
import com.example.composechallengepuppyadoption.ui.PuppyListView
import com.google.gson.Gson

const val PUPPY_DETAIL_VIEW = "puppyDetail"
private const val PUPPY_LIST_SCREEN = "puppyList"
private const val PUPPY = "puppy"

@ExperimentalFoundationApi
@Composable
fun AppNavigator() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PUPPY_LIST_SCREEN) {
        composable(PUPPY_LIST_SCREEN) { PuppyListView(listOfPuppies, navController) }
        composable(
            "$PUPPY_DETAIL_VIEW/{$PUPPY}",
            arguments = listOf(navArgument(PUPPY) { type = NavType.StringType }
            )) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString(PUPPY)?.let { json ->
                val puppy = Gson().fromJson(json, Puppy::class.java)
                PuppyDetailView(puppy = puppy, navController = navController)
            }
        }
    }
}