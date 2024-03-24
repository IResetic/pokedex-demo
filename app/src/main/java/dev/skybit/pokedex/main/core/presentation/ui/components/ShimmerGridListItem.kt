package dev.skybit.pokedex.main.core.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import dev.skybit.pokedex.main.core.presentation.style.defaultRadius
import dev.skybit.pokedex.main.core.presentation.style.largeRadius

@Composable
fun ShimmerGridListItem() {
    Box(
        modifier = Modifier
            .shadow(defaultRadius, RoundedCornerShape(largeRadius))
            .clip(RoundedCornerShape(largeRadius))
            .aspectRatio(1f)
            .background(color = MaterialTheme.colorScheme.surface).shimmerEffect()
    )
}

@Preview(showBackground = false)
@Composable
fun ShimmerGridListItemPreview() {
    ShimmerGridListItem()
}
