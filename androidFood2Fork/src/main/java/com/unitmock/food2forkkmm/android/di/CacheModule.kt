package com.unitmock.food2forkkmm.android.di

import com.unitmock.food2forkkmm.android.BaseApplication
import com.unitmock.food2forkkmm.datasource.cache.*
import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.domain.util.DatetimeUtil
import com.unitmock.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.unitmock.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase {
        return RecipeDatabaseFactory( DriverFactory(context = context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase,
    ): RecipeCache = RecipeCacheImpl(recipeDatabase, DatetimeUtil())
}