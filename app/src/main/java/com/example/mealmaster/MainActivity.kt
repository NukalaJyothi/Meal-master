package com.example.mealmaster

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mealmaster.data.model.Meal
import com.example.mealmaster.ui.screens.HomeScreen
import com.example.mealmaster.ui.screens.RecipeDetailActivity
import com.example.mealmaster.ui.theme.MealMasterAppTheme
import com.example.mealmaster.ui.viewmodel.RecipeViewModel
import com.example.mealmaster.ui.screens.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            setContent {
                MealMasterAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        HomeScreen(
                            recipeViewModel = recipeViewModel,
                            navigateToLogin = {
                                logoutAndNavigateToLogin()
                            },
                            navigateToDetail = { meal ->
                                openRecipeDetail(meal)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun logoutAndNavigateToLogin() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun openRecipeDetail(meal: Meal) {
        val intent = Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra("name", meal.strMeal)
            putExtra("image", meal.strMealThumb)
            putExtra("ingredients", getIngredients(meal))
            putExtra("instructions", meal.strInstructions)
        }
        startActivity(intent)
    }


    private fun getIngredients(meal: Meal): String {
        var ingredients = ""

        with(meal) {
            if (!strIngredient1.isNullOrEmpty()) ingredients += "$strIngredient1 - $strMeasure1\n"
            if (!strIngredient2.isNullOrEmpty()) ingredients += "$strIngredient2 - $strMeasure2\n"
            if (!strIngredient3.isNullOrEmpty()) ingredients += "$strIngredient3 - $strMeasure3\n"
            if (!strIngredient4.isNullOrEmpty()) ingredients += "$strIngredient4 - $strMeasure4\n"
            if (!strIngredient5.isNullOrEmpty()) ingredients += "$strIngredient5 - $strMeasure5\n"
            if (!strIngredient6.isNullOrEmpty()) ingredients += "$strIngredient6 - $strMeasure6\n"
            if (!strIngredient7.isNullOrEmpty()) ingredients += "$strIngredient7 - $strMeasure7\n"
            if (!strIngredient8.isNullOrEmpty()) ingredients += "$strIngredient8 - $strMeasure8\n"
            if (!strIngredient9.isNullOrEmpty()) ingredients += "$strIngredient9 - $strMeasure9\n"
            if (!strIngredient10.isNullOrEmpty()) ingredients += "$strIngredient10 - $strMeasure10\n"
            if (!strIngredient11.isNullOrEmpty()) ingredients += "$strIngredient11 - $strMeasure11\n"
            if (!strIngredient12.isNullOrEmpty()) ingredients += "$strIngredient12 - $strMeasure12\n"
            if (!strIngredient13.isNullOrEmpty()) ingredients += "$strIngredient13 - $strMeasure13\n"
            if (!strIngredient14.isNullOrEmpty()) ingredients += "$strIngredient14 - $strMeasure14\n"
            if (!strIngredient15.isNullOrEmpty()) ingredients += "$strIngredient15 - $strMeasure15\n"
            if (!strIngredient16.isNullOrEmpty()) ingredients += "$strIngredient16 - $strMeasure16\n"
            if (!strIngredient17.isNullOrEmpty()) ingredients += "$strIngredient17 - $strMeasure17\n"
            if (!strIngredient18.isNullOrEmpty()) ingredients += "$strIngredient18 - $strMeasure18\n"
            if (!strIngredient19.isNullOrEmpty()) ingredients += "$strIngredient19 - $strMeasure19\n"
            if (!strIngredient20.isNullOrEmpty()) ingredients += "$strIngredient20 - $strMeasure20\n"
        }
        return ingredients.trimEnd('\n')
    }
}