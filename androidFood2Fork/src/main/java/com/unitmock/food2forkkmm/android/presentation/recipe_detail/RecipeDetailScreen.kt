package com.unitmock.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unitmock.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
) {

    recipe?.let {
        Text(text = "Recipe ID is = ${recipe.title}")
    } ?: run {
        Text(text = "ERROR Recipe ID is null")
    }

}