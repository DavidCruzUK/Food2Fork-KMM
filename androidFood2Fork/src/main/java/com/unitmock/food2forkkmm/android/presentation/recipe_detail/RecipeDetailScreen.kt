package com.unitmock.food2forkkmm.android.presentation.recipe_detail

import android.hardware.TriggerEvent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import com.unitmock.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.unitmock.food2forkkmm.android.presentation.components.RecipeImage
import com.unitmock.food2forkkmm.android.presentation.recipe_detail.components.LoadingRecipeShimmer
import com.unitmock.food2forkkmm.android.presentation.recipe_detail.components.RecipeView
import com.unitmock.food2forkkmm.android.presentation.recipe_list.components.RecipeCard
import com.unitmock.food2forkkmm.android.presentation.theme.AppTheme
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.presentation.recipe_detail.RecipeDetailEvent
import com.unitmock.food2forkkmm.presentation.recipe_detail.RecipeDetailState

@ExperimentalStdlibApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvent) -> Unit,
) {
    AppTheme(
        displayProgressBar = false,
        dialogQueue = state.queue,
    ) {
        state.recipe?.let {
            RecipeView(recipe = it)
        } ?: run {
            if (state.isLoading) {
                LoadingRecipeShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
            } else {
                Text(text = "We Where unable to retrieve the details")
            }
        }
    }

}