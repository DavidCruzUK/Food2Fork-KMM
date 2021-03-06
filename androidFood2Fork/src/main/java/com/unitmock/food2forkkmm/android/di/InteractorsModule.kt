package com.unitmock.food2forkkmm.android.di

import com.unitmock.food2forkkmm.datasource.cache.RecipeCache
import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.unitmock.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache,
    ): SearchRecipes = SearchRecipes(recipeService, recipeCache)

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipe = GetRecipe(recipeCache)
}