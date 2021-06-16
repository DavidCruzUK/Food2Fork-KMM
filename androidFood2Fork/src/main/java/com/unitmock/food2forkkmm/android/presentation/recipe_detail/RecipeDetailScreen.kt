package com.unitmock.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.unitmock.food2forkkmm.android.presentation.components.RecipeImage
import com.unitmock.food2forkkmm.android.presentation.theme.AppTheme
import com.unitmock.food2forkkmm.domain.model.Recipe

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
) {
    AppTheme(displayProgressBar = false) {
        recipe?.let {
            RecipeImage(url = it.featuredImage, contentDescription = it.title)
        } ?: run {
            Text(text = "ERROR Recipe ID is null")
        }
    }

}