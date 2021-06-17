package com.unitmock.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.unitmock.food2forkkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<String>?
) {
    dialogQueue?.peek()?.let { message ->
        GenericDialog(title = "Error", description = message)
    }

}