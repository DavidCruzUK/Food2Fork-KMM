package com.unitmock.food2forkkmm.android.di

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
    ): SearchRecipes = SearchRecipes(recipeService)

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeService: RecipeService,
    ): GetRecipe = GetRecipe(recipeService)
}