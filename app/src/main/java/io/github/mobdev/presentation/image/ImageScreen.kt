package io.github.mobdev.presentation.image

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageScreen(
    imagePath: String,
    onBackPressed: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://faerytea.name/img/$imagePath",
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp, 40.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = CircleShape,
                ),
            onClick = { onBackPressed() },
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}
