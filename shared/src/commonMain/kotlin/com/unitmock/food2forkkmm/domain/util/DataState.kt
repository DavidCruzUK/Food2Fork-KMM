package com.unitmock.food2forkkmm.domain.util

import com.unitmock.food2forkkmm.domain.model.GenericMessageInfo

class DataState<T>(
    val message: GenericMessageInfo? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
) {

    companion object {
        fun <T> error(
            message: GenericMessageInfo,
        ): DataState<T> = DataState(message)

        fun <T> data(
            message: GenericMessageInfo? = null,
            data: T? = null,
        ): DataState<T> = DataState(message, data)

        fun <T> loading() = DataState<T>(isLoading = true)
    }
}