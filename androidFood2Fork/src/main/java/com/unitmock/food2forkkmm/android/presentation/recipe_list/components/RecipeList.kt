package com.unitmock.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.unitmock.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT
import com.unitmock.food2forkkmm.datasource.network.RecipeServiceImpl
import com.unitmock.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes:List<Recipe>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    if (loading && recipes.isEmpty()) {
        LoadingRecipeListShimmer(RECIPE_IMAGE_HEIGHT.dp)
    } else if(recipes.isEmpty()) {

    } else {
        LazyColumn {
            itemsIndexed(
                items = recipes
            ) { index, recipe ->
                if ((index +1) >= (page * RecipeServiceImpl.RECIPE_PAGINATION_PAGE_SIZE) && !loading) {
                    onTriggerNextPage()
                }
                RecipeCard(recipe = recipe, onClick = { onClickRecipeListItem(recipe.id) })
            }
        }
    }
}
