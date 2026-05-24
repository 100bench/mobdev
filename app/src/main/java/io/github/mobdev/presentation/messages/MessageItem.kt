package io.github.mobdev.presentation.messages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.mobdev.domain.model.Message
import io.github.mobdev.domain.model.MessageContent

@Composable
fun MessageItem(
    message: Message,
    onImageClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = message.from,
            style = MaterialTheme.typography.labelMedium,
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        when (val content = message.content) {
            is MessageContent.Text -> {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    tonalElevation = 2.dp,
                ) {
                    Text(
                        text = content.text,
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 8.dp,
                        )
                    )
                }
            }

            is MessageContent.Image -> {
                AsyncImage(
                    model = "https://faerytea.name/thumb/${content.imagePath}",
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            onImageClick(
                                content.imagePath
                            )
                        },
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}
