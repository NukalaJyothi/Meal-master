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
        val ingredients = mutableListOf<String>()

        for (i in 1..20) {
            try {
                val ingredientField = meal::class.java.getDeclaredField("strIngredient$i")
                val measureField = meal::class.java.getDeclaredField("strMeasure$i")

                ingredientField.isAccessible = true
                measureField.isAccessible = true

                val ingredient = ingredientField.get(meal) as? String
                val measure = measureField.get(meal) as? String

                if (!ingredient.isNullOrEmpty()) {
                    ingredients.add("$ingredient - $measure")
                }
            }
            catch (_: NoSuchFieldException) {}
            catch (_: IllegalAccessException) {}
        }
        return ingredients.joinToString("\n")
    }
}