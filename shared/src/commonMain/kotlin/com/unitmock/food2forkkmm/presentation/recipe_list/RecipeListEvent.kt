package com.unitmock.food2forkkmm.presentation.recipe_list

sealed class RecipeListEvent {

    object LoadRecipes : RecipeListEvent()

    object NextPage : RecipeListEvent()

    object NewSearch : RecipeListEvent()

    data class OnUpdateQuery(val query: String) : RecipeListEvent()

    data class OnSelectCategory(val category: FoodCategory) : RecipeListEvent()

    object OnRemoveHeadMessageFromQueue : RecipeListEvent()

}