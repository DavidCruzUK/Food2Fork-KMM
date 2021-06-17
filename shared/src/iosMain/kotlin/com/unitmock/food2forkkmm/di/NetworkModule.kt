package com.unitmock.food2forkkmm.di

import com.unitmock.food2forkkmm.datasource.network.KtorClientFactory
import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.datasource.network.RecipeServiceImpl
import com.unitmock.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.BASE_URL

class NetworkModule {

    val recipeService: RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = BASE_URL,
        )
    }
}