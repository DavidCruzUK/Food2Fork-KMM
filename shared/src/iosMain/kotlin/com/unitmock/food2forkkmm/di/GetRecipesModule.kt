package com.unitmock.food2forkkmm.di

import com.unitmock.food2forkkmm.interactors.recipe_detail.GetRecipe

class GetRecipesModule(
    private val cacheModule: CacheModule,
) {
    val getRecipe: GetRecipe by lazy {
        GetRecipe(
            recipeCache = cacheModule.recipeCache
        )
    }

}