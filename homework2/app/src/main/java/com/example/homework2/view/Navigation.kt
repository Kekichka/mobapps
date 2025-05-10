package com.example.homework2.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.homework2.viewmodel.NoteViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel: NoteViewModel, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "HomeScreen", modifier = modifier) {
        composable("HomeScreen") {
            HomeScreen(navController, viewModel)
        }

        composable("AddNoteScreen") {
            AddNoteScreen(navController, viewModel)
        }
        composable(
            route = "NoteDetail/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.IntType }
            )

        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            NoteDetailScreen(navController, noteId, viewModel)
        }
    }
}