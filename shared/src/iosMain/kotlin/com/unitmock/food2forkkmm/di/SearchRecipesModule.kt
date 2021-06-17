package com.unitmock.food2forkkmm.di

import com.unitmock.food2forkkmm.interactors.recipe_list.SearchRecipes

class SearchRecipesModule(
    private val networkModule: NetworkModule,
    private val cacheModule: CacheModule,
) {
    val searchRecipes: SearchRecipes by lazy {
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cacheModule.recipeCache
        )
    }

}