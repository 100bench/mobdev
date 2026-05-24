package io.github.mobdev.presentation.chats

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mobdev.presentation.theme.BluePrimary
import io.github.mobdev.presentation.theme.DividerColor

@Composable
fun ChatItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                width = 0.5.dp,
                color = if (selected) {
                    BluePrimary
                } else {
                    DividerColor
                },
            ),
        tonalElevation = if (selected) 4.dp else 0.dp,
        color = if (selected) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            MaterialTheme.colorScheme.surface
        }
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 20.dp,
            ),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}
