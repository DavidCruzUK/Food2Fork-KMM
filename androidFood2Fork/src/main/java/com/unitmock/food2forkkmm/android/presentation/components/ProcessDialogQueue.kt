package com.unitmock.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.unitmock.food2forkkmm.domain.model.GenericMessageInfo
import com.unitmock.food2forkkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadFromQueue: () -> Unit,
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(title = dialogInfo.title, description = dialogInfo.description, onRemoveHeadFromQueue = onRemoveHeadFromQueue)
    }

}