package com.unitmock.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.unitmock.food2forkkmm.android.presentation.components.RecipeImage
import com.unitmock.food2forkkmm.android.presentation.recipe_detail.components.RecipeView
import com.unitmock.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.unitmock.food2forkkmm.android.presentation.theme.AppTheme
import com.unitmock.food2forkkmm.domain.model.Recipe

@ExperimentalStdlibApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
) {
    AppTheme(displayProgressBar = false) {
        recipe?.let {
            RecipeView(recipe = recipe)
        } ?: run {
            Text(text = "ERROR Recipe ID is null")
        }
    }

}