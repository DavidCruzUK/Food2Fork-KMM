package com.unitmock.food2forkkmm.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.unitmock.food2forkkmm.presentation.recipe_list.RecipeListEvent
import com.unitmock.food2forkkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchRecipes: SearchRecipes,
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvent.LoadRecipes)
    }

    fun onTriggerEvent(event:RecipeListEvent) {
        when (event) {
            RecipeListEvent.LoadRecipes -> loadRecipes()
            RecipeListEvent.NextPage -> nextPage()
            RecipeListEvent.NewSearch -> nextSearch()
            is RecipeListEvent.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query)
            }
            else -> handleError("")
        }
    }

    private fun nextSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun handleError(errorMessage: String) {
        // TODO("Not yet implemented")
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query,
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipes ->
                state.value = state.value.copy(recipes = recipes)
            }
            dataState.message?.let { handleError(it) }
        }.launchIn(viewModelScope)
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(state.value.recipes)
        current.addAll(recipes)
        state.value = state.value.copy(recipes = current)
    }

}