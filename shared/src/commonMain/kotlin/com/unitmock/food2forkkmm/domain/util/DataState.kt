package com.unitmock.food2forkkmm.domain.util

class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
) {

    companion object {
        fun <T> error(
            message: String,
        ): DataState<T> = DataState(message)

        fun <T> data(
            message: String?= null,
            data: T? = null,
        ): DataState<T> = DataState(message, data)

        fun <T> loading() = DataState<T>(isLoading = true)
    }
}