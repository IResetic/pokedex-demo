package dev.skybit.pokedex.main.core.presentation.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import dev.skybit.pokedex.main.core.presentation.style.fifteenPercent
import dev.skybit.pokedex.main.core.presentation.style.thirtyPercent
import dev.skybit.pokedex.main.core.utils.SHIMMER_ANIMATION_DURATION

fun Modifier.shimmerEffect(brush: Brush? = null): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "InfiniteTransition")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(SHIMMER_ANIMATION_DURATION)),
        label = "FloatAnimation"
    )

    val backgroundColor = MaterialTheme.colorScheme.onBackground

    background(
        brush = brush ?: Brush.linearGradient(
            colors = listOf(
                backgroundColor.copy(alpha = thirtyPercent),
                backgroundColor.copy(alpha = fifteenPercent),
                backgroundColor.copy(alpha = thirtyPercent)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned { size = it.size }
}
