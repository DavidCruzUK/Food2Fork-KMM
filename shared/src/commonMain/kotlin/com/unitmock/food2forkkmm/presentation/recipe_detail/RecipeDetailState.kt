package com.unitmock.food2forkkmm.presentation.recipe_detail

import com.unitmock.food2forkkmm.domain.model.GenericMessageInfo
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.util.Queue

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val queue: Queue<GenericMessageInfo> = Queue(mutableListOf())
)