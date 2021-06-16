package com.unitmock.food2forkkmm.android.presentation.recipe_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
) {

    recipeId?.let {
        Text(text = "Recipe ID is = $recipeId")
    } ?: run {
        Text(text = "ERROR Recipe ID is null =$recipeId")
    }

}