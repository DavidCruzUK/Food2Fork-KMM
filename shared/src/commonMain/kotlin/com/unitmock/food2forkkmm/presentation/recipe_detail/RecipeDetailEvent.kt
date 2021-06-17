package com.unitmock.food2forkkmm.presentation.recipe_detail

sealed class RecipeDetailEvent {
    data class GetRecipe(val recipeId: Int): RecipeDetailEvent()
}