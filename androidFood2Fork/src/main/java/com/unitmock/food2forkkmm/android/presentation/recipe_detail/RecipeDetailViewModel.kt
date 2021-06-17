package com.unitmock.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unitmock.food2forkkmm.domain.model.GenericMessageInfo
import com.unitmock.food2forkkmm.domain.model.UIComponentType
import com.unitmock.food2forkkmm.domain.util.GenericMessageInfoQueueUtil
import com.unitmock.food2forkkmm.domain.util.Queue
import com.unitmock.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.unitmock.food2forkkmm.presentation.recipe_detail.RecipeDetailEvent
import com.unitmock.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
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
        when (event) {
            is RecipeDetailEvent.GetRecipe -> getRecipe(event.recipeId)
            is RecipeDetailEvent.OnRemoveHeadMessageFromQueue -> removeHeadMessageFromQueue()
            else -> appendToMessageQueue(
                GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Invalid State")
                    .build()
            )
        }
    }

    private fun removeHeadMessageFromQueue() {
        try {
            val queue = state.value.queue
            queue.remove()
            state.value = state.value.copy(queue = Queue(mutableListOf())) // Force Recompose
            state.value = state.value.copy(queue = queue)
        } catch (e: Exception) {

        }
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo) {
        if (!GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = messageInfo
            )
        ) {
            val queue = state.value.queue
            queue.add(messageInfo)
            state.value = state.value.copy(queue = queue)
        }
    }

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipe ->
                this.state.value = state.value.copy(recipe = recipe)
            }
            dataState.message?.let {
                appendToMessageQueue(it)
            }
        }.launchIn(viewModelScope)
    }
}