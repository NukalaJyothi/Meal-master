package com.example.mealmaster.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.mealmaster.ui.theme.MealMasterAppTheme

class RecipeDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeName = intent.getStringExtra("name")
        val recipeImage = intent.getStringExtra("image")
        val recipeIngredients = intent.getStringExtra("ingredients")
        val recipeInstructions = intent.getStringExtra("instructions")

        setContent {
            MealMasterAppTheme {
                RecipeDetailScreen(
                    recipeName = recipeName ?: "No Title",
                    recipeImage = recipeImage,
                    recipeIngredients = recipeIngredients ?: "No ingredients",
                    recipeInstructions = recipeInstructions ?: "No instructions"
                )
            }
        }
    }
}


@Composable
fun RecipeDetailScreen(
    recipeName: String,
    recipeImage: String?,
    recipeIngredients: String,
    recipeInstructions: String
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                text = recipeName,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(bottom = 15.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )

            recipeImage?.let {
                AsyncImage(
                    model = it,
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surface)
                )
            }

            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = recipeIngredients,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Instructions",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = recipeInstructions,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(
        recipeName = "Spaghetti Bold",
        recipeImage = "https://www.example.com/spaghetti.jpg",
        recipeIngredients = "Spaghetti, Ground Beef, Tomato Sauce, Garlic, Onion, Olive Oil, Salt, Pepper",
        recipeInstructions = "1. Boil spaghetti. 2. Cook beef with garlic and onion. 3. Add tomato sauce and simmer."
    )
}