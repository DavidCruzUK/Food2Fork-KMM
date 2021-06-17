package com.unitmock.food2forkkmm.interactors.recipe_list

import com.unitmock.food2forkkmm.datasource.cache.RecipeCache
import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.domain.model.GenericMessageInfo
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.model.UIComponentType
import com.unitmock.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())

        try {
            val recipes = recipeService.search(page, query)

            delay(500)

            if (query == "error"){
                throw Exception("new errors")
            }

            recipeCache.insert(recipes)

            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page)
            } else {
                recipeCache.search(query, page)
            }

            emit(DataState.data(data = cacheResult))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(message = GenericMessageInfo.Builder()
                .id("SearchRecipe.Error")
                .title("Error")
                .uiComponentType(UIComponentType.Diaslog)
                .description(e.message ?: "Unknown Error")
                .build()
            ))
        }
    }
}