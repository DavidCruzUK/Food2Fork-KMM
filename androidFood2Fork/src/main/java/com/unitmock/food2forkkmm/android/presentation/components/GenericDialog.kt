package com.unitmock.food2forkkmm.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unitmock.food2forkkmm.domain.model.NegativeAction
import com.unitmock.food2forkkmm.domain.model.PositiveAction

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    title: String,
    description: String? = null,
    positiveAction: PositiveAction?,
    negativeAction: NegativeAction?,
    onRemoveHeadFromQueue: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss?.invoke()
            onRemoveHeadFromQueue()
        },
        text = {
            description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h3
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                if (negativeAction != null) Button(
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
                    onClick = {
                        negativeAction.onNegativeAction()
                        onRemoveHeadFromQueue()
                    }
                ) {
                    Text(
                        text = negativeAction.negativeBtnTxt,
                        style = MaterialTheme.typography.button
                    )
                }
                if (positiveAction != null) Button(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = {
                        positiveAction.onPositiveAction()
                        onRemoveHeadFromQueue()
                    }
                ) {
                    Text(
                        text = positiveAction.positiveBtnTxt,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    )
}