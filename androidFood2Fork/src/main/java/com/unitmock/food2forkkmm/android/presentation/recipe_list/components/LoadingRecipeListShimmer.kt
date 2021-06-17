package com.unitmock.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.unitmock.food2forkkmm.android.presentation.components.ShimmerRecipeCardItem

@Composable
fun LoadingRecipeListShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val cardWithPx = with(LocalDensity.current) {
            (maxWidth - (padding * 2)).toPx()
        }
        val cardHeightPx = with(LocalDensity.current) {
            (imageHeight - padding).toPx()
        }
        val gradientWidth: Float = (0.2f * cardHeightPx)

        val inifineTransition = rememberInfiniteTransition()
        val xCardShimmer = inifineTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardWithPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val yCardShimmer = inifineTransition.animateFloat(
            initialValue = 0f,
            targetValue = (cardHeightPx + gradientWidth),
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1300,
                    easing = LinearEasing,
                    delayMillis = 300,
                ),
                repeatMode = RepeatMode.Restart
            )
        )

        val colors = listOf(
            Color.LightGray.copy(0.9f),
            Color.LightGray.copy(0.3f),
            Color.LightGray.copy(0.9f),
        )

        LazyColumn {
            items(5) {
                ShimmerRecipeCardItem(
                    colors = colors,
                    xShimmer = xCardShimmer.value,
                    yShimmer = yCardShimmer.value,
                    cardHeight = imageHeight,
                    gradientWidth = gradientWidth,
                    padding = padding
                )
            }
        }

    }
}