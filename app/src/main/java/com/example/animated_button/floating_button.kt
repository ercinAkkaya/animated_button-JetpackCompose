package com.example.animated_button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FloatingButton(title: String, onClick: () -> Unit, enabled: Boolean) {
    var visible by remember { mutableStateOf(false) }
    var offsetX by remember { mutableFloatStateOf(50f) }
    val animatedOffsetX by animateFloatAsState(targetValue = offsetX, label = "")


    LaunchedEffect(Unit) {
        delay(200)
        visible = true
        offsetX = 0f
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(200.dp, 40.dp)
            .offset(x = animatedOffsetX.dp)
            .background(
                color = if (enabled) Color(0xFF1E2A5E) else Color.Gray,
                shape = RoundedCornerShape(16.dp)
            )
            .then(if (enabled) Modifier.clickable(onClick = onClick) else Modifier)
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn()
        ) {
            Text(text = title, color = Color.White)
        }
    }
}