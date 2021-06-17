package com.unitmock.food2forkkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.unitmock.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.unitmock.food2forkkmm.android.presentation.theme.AppTheme
import com.unitmock.food2forkkmm.presentation.recipe_list.RecipeListEvent
import com.unitmock.food2forkkmm.presentation.recipe_list.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvent) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(displayProgressBar = false) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            page = state.page,
            onTriggerNextPage = { onTriggerEvent(RecipeListEvent.NextPage) },
            onClickRecipeListItem = onClickRecipeListItem
        )
    }

}