package com.unitmock.food2forkkmm.interactors.recipe_detail

import com.unitmock.food2forkkmm.datasource.cache.RecipeCache
import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.domain.model.GenericMessageInfo
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.model.UIComponentType
import com.unitmock.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache,
) {
    fun execute(
        id: Int
    ): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())

        try {
            val recipe = recipeCache.get(id)
            delay(500)
            emit(DataState.data(data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = GenericMessageInfo.Builder()
                .id("GetRecipe.Error")
                .title("Error")
                .uiComponentType(UIComponentType.Diaslog)
                .description(e.message ?: "Unknown Error")
                .build()
            ))
        }
    }
}