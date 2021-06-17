package com.unitmock.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unitmock.food2forkkmm.datasource.network.RecipeService
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.util.DatetimeUtil
import com.unitmock.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.unitmock.food2forkkmm.presentation.recipe_detail.RecipeDetailEvent
import com.unitmock.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe,
) : ViewModel() {
    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let { recipeId ->
            viewModelScope.launch {
                onTriggerEvent(RecipeDetailEvent.GetRecipe(recipeId = recipeId))
            }
        }
    }

    fun onTriggerEvent(event: RecipeDetailEvent) {
        when(event) {
            is RecipeDetailEvent.GetRecipe -> getRecipe(event.recipeId)
            else -> handleError("Invalid Event")
        }
    }

    private fun handleError(errorMessage: String) {
        val queue = state.value.queue
        queue.add(errorMessage)
        state.value = state.value.copy(queue = queue)
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId).onEach {dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let {recipe ->
                this.state.value = state.value.copy(recipe = recipe)
            }
            dataState.message?.let {
                handleError(it)
            }
        }.launchIn(viewModelScope)
    }
}