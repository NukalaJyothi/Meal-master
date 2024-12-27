package com.example.mealmaster.ui.viewmodel

sealed class RecipeViewIntent {
    data object LoadRandomRecipe : RecipeViewIntent()
    data class SearchRecipes(val query: String) : RecipeViewIntent()
}