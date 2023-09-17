package com.whoisthat.pokemon.ui.widget

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val shimmerColors = listOf(
    Color.LightGray.copy(alpha = 0.8f),
    Color.LightGray.copy(alpha = 0.3f),
    Color.LightGray.copy(alpha = 0.6f)
)

@Composable
fun shimmerBrush(
    showShimmer: Boolean = true,
    targetValue: Float = 1000f
): Brush {
    return if (showShimmer) {
        val transition = rememberInfiniteTransition(label = "shimmer.animation.infinite")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse
            ), label = "shimmer.animation.translate"
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}