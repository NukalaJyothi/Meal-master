package com.example.mealmaster.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mealmaster.ui.components.ErrorComponent
import com.example.mealmaster.ui.components.SuccessComponent
import com.example.mealmaster.ui.viewmodel.RecipeViewIntent
import com.example.mealmaster.ui.viewmodel.RecipeViewModel
import com.example.mealmaster.ui.viewmodel.RecipeViewState

@Composable
fun HomeScreen(recipeViewModel: RecipeViewModel) {
    val state by recipeViewModel.state

    when (state) {
        is RecipeViewState.Loading -> {

            Box(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Meal Master",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp
                )
            }
        }
        is RecipeViewState.Success -> {
            val recipes = (state as RecipeViewState.Success).recipes
            SuccessComponent(
                recipes = recipes,
                onSearchClicked = { query ->
                    recipeViewModel.processIntent(RecipeViewIntent.SearchRecipes(query))
                }
            )
        }
        is RecipeViewState.Error -> {
            val message = (state as RecipeViewState.Error).message
            ErrorComponent(
                message = message,
                onRefreshClicked = {
                    recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        recipeViewModel.processIntent(RecipeViewIntent.LoadRandomRecipe)
    }
}
