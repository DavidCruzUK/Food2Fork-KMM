package com.unitmock.food2forkkmm.presentation.recipe_list

sealed class RecipeListEvent {

    object LoadRecipes :RecipeListEvent()

    object NextPage :RecipeListEvent()

}