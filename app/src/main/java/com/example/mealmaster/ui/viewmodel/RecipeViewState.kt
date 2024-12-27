package com.example.mealmaster.ui.viewmodel

import com.example.mealmaster.data.model.Meal

sealed class RecipeViewState {
    data object Loading: RecipeViewState()
    data class Success(val recipes: List<Meal>): RecipeViewState()
    data class Error(val message: String): RecipeViewState()
}