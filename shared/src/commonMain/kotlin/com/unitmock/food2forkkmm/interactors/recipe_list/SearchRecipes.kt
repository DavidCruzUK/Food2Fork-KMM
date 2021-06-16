package com.unitmock.food2forkkmm.interactors.recipe_list

import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())

        try {
            val recipes = recipeService.search(page, query)
            emit(DataState.data(data = recipes))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unknown Error"))
        }
    }
}