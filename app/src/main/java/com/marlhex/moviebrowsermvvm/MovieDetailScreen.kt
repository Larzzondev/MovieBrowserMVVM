package com.marlhex.moviebrowsermvvm

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay

@Composable
fun MovieDetailScreen(
    title: String,
    imageUrl: String
) {
    // Ensure correct image URL with base TMDB path
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val finalImageUrl = if (imageUrl.startsWith("http")) Uri.decode(imageUrl) else "$baseUrl${Uri.decode(imageUrl)}"

    var imageVisible by remember { mutableStateOf(false) }

    // Delay animation for smooth transition
    LaunchedEffect(Unit) {
        delay(300)
        imageVisible = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Animated fade-in effect
        AnimatedVisibility(
            visible = imageVisible,
            enter = androidx.compose.animation.fadeIn(animationSpec = tween(1000))
        ) {
            AsyncImage(
                model = finalImageUrl,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}