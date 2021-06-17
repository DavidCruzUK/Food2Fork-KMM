package com.unitmock.food2forkkmm.presentation.recipe_detail

import com.unitmock.food2forkkmm.domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
)