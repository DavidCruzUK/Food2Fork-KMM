package com.unitmock.food2forkkmm.interactors.recipe_detail

import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeService: RecipeService,
) {
    fun execute(
        id: Int
    ): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())

        try {
            val recipe = recipeService.get(id)
            emit(DataState.data(data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown Error"))
        }
    }
}